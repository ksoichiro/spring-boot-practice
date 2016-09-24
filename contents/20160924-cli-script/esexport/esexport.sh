#!/bin/bash

HOST=localhost
PORT=9200
BALANCE_FROM=$1
BALANCE_TO=$2

curl -Ss --noproxy ${HOST} http://${HOST}:${PORT}/bank/account/_search -d '{
    "query": {
        "filtered": {
            "filter": {
                "bool": {
                    "must": {
                        "range": {
                            "balance": {
                                "from": '${BALANCE_FROM}',
                                "to": '${BALANCE_TO}'
                            }
                        }
                    }
                }
            }
        }
    }
}' | jq -c -M '.hits.hits[]' | while read line
    do
        echo $line | jq -c -M '{index: {_id: ._id}}'
        echo $line | jq -c -M '._source'
    done
