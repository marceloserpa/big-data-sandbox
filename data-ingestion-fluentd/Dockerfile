FROM ubuntu

# update, curl, sudo
RUN apt-get update && apt-get -y upgrade
RUN apt-get -y install curl 
RUN apt-get -y install sudo

RUN useradd -m docker && echo "docker:docker" | chpasswd && adduser docker sudo

# ruby and snappy install
RUN apt-get install -y build-essential autoconf automake libtool libsnappy-dev ruby2.3-dev
RUN gem install snappy

# install fluentd
RUN curl -O http://packages.treasure-data.com/debian/RPM-GPG-KEY-td-agent && apt-key add RPM-GPG-KEY-td-agent && rm RPM-GPG-KEY-td-agent
RUN curl -L http://toolbelt.treasuredata.com/sh/install-ubuntu-precise-td-agent2.sh | sh 
#ADD td-agent.conf /etc/td-agent/td-agent.conf
RUN curl -L https://raw.githubusercontent.com/fluent/fluentd/master/COPYING > /fluentd-license.txt

# install fluentd-kafka plugin
RUN gem install fluent-plugin-kafka

# install node
RUN apt-get -y install nodejs 
RUN apt-get -y install npm

# preparing application sample
COPY app/. /usr/src/app_folder
RUN chmod -R 777 /usr/src/app_folder
RUN cd /usr/src/app_folder && npm install

# adding td-agent
ADD td-agent.conf /etc/td-agent/td-agent.conf

EXPOSE 80

# start fluent
ENTRYPOINT /etc/init.d/td-agent restart \ 
    && nodejs /usr/src/app_folder/application-logger.js \
    &&  /bin/bash

# see td-agent's logs using: tail -f /var/log/td-agent/td-agent.log