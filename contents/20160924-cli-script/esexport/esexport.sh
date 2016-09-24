#!/bin/bash

HOST=localhost
PORT=9200
BALANCE_FROM=$1
BALANCE_TO=$2

PAGE_FROM=0
PAGE_SIZE=100

while :
do
    result=`curl -Ss --noproxy ${HOST} http://${HOST}:${PORT}/bank/account/_search -d '{
        "from": '${PAGE_FROM}',
        "size": '${PAGE_SIZE}',
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
    }'`

    if [ 0 -eq `echo "$result" | jq -c -M '.hits.hits[]' | wc -l` ]; then
        break
    fi

    echo "$result" | jq -c -M '.hits.hits[]' | while read line
    do
        echo $line | jq -c -M '{index: {_id: ._id}}'
        echo $line | jq -c -M '._source'
    done

    PAGE_FROM=`expr ${PAGE_FROM} + ${PAGE_SIZE}`
done
