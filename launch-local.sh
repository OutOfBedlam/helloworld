#!/bin/bash
docker-compose up -d
../logstash-test/logstash/bin/logstash --debug --verbose -f src/univeral/logstash/shipper-pipeline2.conf &
