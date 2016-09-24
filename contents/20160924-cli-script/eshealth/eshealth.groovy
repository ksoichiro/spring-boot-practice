@ConfigurationProperties(prefix = 'es')
class EsHealthConfigProperties {
    String host = 'localhost'
    int port = 9200
}

@Component
@ConfigurationProperties(prefix = 'es')
class EsHealth implements CommandLineRunner {
    EsHealthConfigProperties esConfig

    @Autowired
    EsHealth(EsHealthConfigProperties esConfig) {
        this.esConfig = esConfig
    }

    @Override
    public void run(String... args) {
        println "curl http://${esConfig.host}:${esConfig.port}/_cluster/health?pretty=true".execute().text
    }
}
