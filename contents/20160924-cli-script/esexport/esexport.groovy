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
@ConfigurationProperties(prefix = 'es')
class EsExport implements CommandLineRunner {
    EsExportConfigProperties esConfig

    @Autowired
    EsExport(EsExportConfigProperties esConfig) {
        this.esConfig = esConfig
    }

    @Override
    void run(String... args) {
        def json = new JsonBuilder()
        json {
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
        def command = "curl --noproxy ${esConfig.host} http://${esConfig.host}:${esConfig.port}/bank/account/_search -d ${json.toString()}"
        def result = new JsonSlurper().parseText(command.execute().text).hits.hits
        result.each { hit ->
            println JsonOutput.toJson([index: [_id: hit._id]])
            println JsonOutput.toJson(hit._source)
        }
    }
}
