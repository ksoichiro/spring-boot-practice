# Elasticsearch server

If you want to use Elasticsearch for test purpose, this would be one of the easiest way to do it.

## Run

```
spring run elasticsearch.groovy
```

If you want another cluster on the same machine:

```
spring run elasticsearch.groovy -- --server.port=8081 --spring.data.elasticsearch.cluster-name=elasticsearch2
```

## Create jar

```
spring jar elasticsearch.jar elasticsearch.groovy
```
