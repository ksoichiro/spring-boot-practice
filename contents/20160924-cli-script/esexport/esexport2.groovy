@Grab('groovy-all')
@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7')

import groovy.json.*
import groovyx.net.http.*
import static groovyx.net.http.ContentType.*

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
        def http = new RESTClient("http://${esConfig.host}:${esConfig.port}/")
        while (true) {
            def result = http.post(path: 'bank/account/_search', contentType: JSON, body: json.toString()).data
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
