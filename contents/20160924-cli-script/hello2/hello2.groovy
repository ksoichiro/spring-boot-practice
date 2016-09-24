@Component
class Hello implements CommandLineRunner {
    @Override
    public void run(String... args) {
        println "Hello, world!"
        if (args) {
            for (def arg : args) {
                println arg
            }
        }
    }
}
