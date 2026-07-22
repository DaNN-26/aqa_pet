#!/bin/bash
docker run -d \
    --name selenoid \
    -p 4444:4444 \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v ${PWD}/selenoid-config:/etc/selenoid \
    aerokube/selenoid:latest-release

/usr/local/bin/wait-for-selenoid.sh