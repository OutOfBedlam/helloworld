FROM dnvriend/logstash

ADD /target/universal/helloworld.tgz /opt

RUN chmod +x /opt/helloworld/bin/logstash.sh

RUN chmod +x /opt/helloworld/bin/helloworld

RUN chmod +x /opt/helloworld/bin/launch.sh

ENTRYPOINT ["/opt/helloworld/bin/launch.sh"]
