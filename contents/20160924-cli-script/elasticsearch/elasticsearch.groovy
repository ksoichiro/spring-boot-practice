@Grab('spring-boot-starter-data-elasticsearch')
@RestController
class Elasticsearch {
    @RequestMapping("/")
    String index() {
        "Hello, world!"
    }
}
