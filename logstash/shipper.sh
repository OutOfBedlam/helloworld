#!/bin/bash
#logstash/bin/logstash --debug --verbose --configtest -f conf/first-pipeline.conf
logstash/bin/logstash --debug --verbose -f conf/shipper-pipeline.conf