## Elasticsearch Export

This example exports data from Elasticsearch with filter
and output JSON file for bulk API.

The file `accounts.json` is copied from [the official Elasticsearch documents](https://www.elastic.co/guide/en/elasticsearch/reference/current/_exploring_your_data.html).

## Prepare: import sample data set

```
curl -XPOST 'localhost:9200/bank/account/_bulk?pretty' --data-binary "@accounts.json"
```

## Run

Using curl for HTTP client:

```
spring run esexport.groovy -- --es.balanceFrom=20000 --es.balanceTo=30000 > export.json
```

No dependencies to external commands:

```
spring run esexport2.groovy -- --es.balanceFrom=20000 --es.balanceTo=30000 > export.json
```

Shell script with [jq](https://stedolan.github.io/jq/):

```
./esexport.sh 20000 30000 > export.json
```

## Import to another Elasticsearch cluster

```
curl -XPOST http://localhost:9201/bank/account/_bulk --data-binary "@export.json"
```
