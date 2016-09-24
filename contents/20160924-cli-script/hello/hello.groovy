@Component
class Hello implements CommandLineRunner {
    @Override
    public void run(String... args) {
        println "Hello, world!"
    }
}
