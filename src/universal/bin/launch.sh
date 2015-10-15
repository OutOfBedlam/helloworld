#!/bin/bash
touch /tmp/helloworld.log
/opt/helloworld/bin/helloworld &
/opt/helloworld/bin/logstash.sh &
tail -f /tmp/helloworld.log