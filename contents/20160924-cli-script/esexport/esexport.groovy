@Grab('groovy-all')

import groovy.json.*

@ConfigurationProperties(prefix = 'es')
class EsExportConfigProperties {
    String host = 'localhost'
    int port = 9200
    int balanceFrom = -1
    int balanceTo = -1
}

@Component
class EsExport implements CommandLineRunner {
    @Autowired
    EsExportConfigProperties esConfig

    @Override
    void run(String... args) {
        def json = new JsonBuilder()
        json {
            from 0
            size 100
            query {
                filtered {
                    filter {
                        bool {
                            must {
                                range {
                                    balance {
                                        from esConfig.balanceFrom
                                        to esConfig.balanceTo
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        while (true) {
            def command = "curl --noproxy ${esConfig.host} http://${esConfig.host}:${esConfig.port}/bank/account/_search -d ${json.toString()}"
            def result = new JsonSlurper().parseText(command.execute().text)
            if (result.hits.hits.size() == 0) {
                break
            }
            result.hits.hits.each { hit ->
                println JsonOutput.toJson([index: [_id: hit._id]])
                println JsonOutput.toJson(hit._source)
            }
            json.content.from += json.content.size
        }
    }
}
