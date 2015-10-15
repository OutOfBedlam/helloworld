#!/bin/bash
JMXCONF_PATH="${JMXCONF_PATH:-/tmp/jmxconf}"

LOGSTASH_TEMPLATE=" \
input { \
   jmx { \
    path => \"$JMXCONF_PATH\" \
    polling_frequency => 1 \
    type => \"jmx\" \
    nb_thread => 4 \
  } \
} \
 \
output { \
  stdout { \
	  codec => rubydebug  \
  }	\
}"

echo $LOGSTASH_TEMPLATE > /tmp/logstash-config.conf
#../logstash-test/logstash/bin/logstash --debug --verbose -f /tmp/logstash-config.conf
#../logstash-test/logstash/bin/logstash --debug --verbose -e "LOGSTASH_TEMPLATE"