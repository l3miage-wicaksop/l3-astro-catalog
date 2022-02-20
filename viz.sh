#!/bin/sh

docker run \
    --name myadmin \
    --rm --detach \
    --link mariadb:db \
    -p 9090:80 \
    phpmyadmin
