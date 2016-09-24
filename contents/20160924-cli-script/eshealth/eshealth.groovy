@ConfigurationProperties(prefix = 'es')
class EsHealthConfigProperties {
    String host = 'localhost'
    int port = 9200
}

@Component
class EsHealth implements CommandLineRunner {
    @Autowired
    EsHealthConfigProperties esConfig

    @Override
    void run(String... args) {
        println "curl http://${esConfig.host}:${esConfig.port}/_cluster/health?pretty=true".execute().text
    }
}
