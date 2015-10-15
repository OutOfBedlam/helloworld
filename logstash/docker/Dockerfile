FROM java:8

MAINTAINER dnvriend@gmail.com

RUN apt-get update && apt-get install nano wget -y

RUN wget https://download.elastic.co/logstash/logstash/logstash-1.5.4.tar.gz -O /opt/logstash-1.5.4.tar.gz

RUN tar xvf /opt/logstash-1.5.4.tar.gz -C /opt

RUN rm /opt/logstash-1.5.4.tar.gz

RUN ln -s /opt/logstash-1.5.4 /opt/logstash

# install logstash-input-jmx
RUN /opt/logstash/bin/plugin install logstash-input-jmx
