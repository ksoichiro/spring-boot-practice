# elasticsearch

## Launch

```
./gradlew bootRun
```

## Example queries

Search with filter:

```
curl -s localhost:9200/bank/account/_search -d '{
  "query":{
    "filtered":{
      "query":{"match_all":{}},
      "filter":{
        "bool":{
          "must": [
            { "term": { "age": 34 } }
          ]
        }
      }
    }
  }
}' | jq .
```

Search by analyzed (string) field:

```
curl -s localhost:9200/bank/account/_search -d '{
  "query":{
    "filtered":{
      "query":{"match": { "gender": "F" }},
      "filter":{
        "bool":{
          "must": [
            { "term": { "age": 34 } }
          ]
        }
      }
    }
  }
}' | jq .
```

Get total:

```
curl -s localhost:9200/bank/account/_search -d '{
  "query":{
    "filtered":{
      "query":{"match_all":{}},
      "filter":{
        "bool":{
          "must": [
            { "term": { "age": 34 } }
          ]
        }
      }
    }
  }
}' | jq '.hits.total'
```
