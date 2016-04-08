#!/bin/bash

for i in $(find ./contents -type d -maxdepth 1); do
    pushd ${i} > /dev/null 2>&1
    if [ -f gradlew ]; then
        echo "Build ${i}"
        ./gradlew build
    fi
    popd > /dev/null 2>&1
done
