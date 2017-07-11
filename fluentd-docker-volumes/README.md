
# FluentD



sudo docker build . -t fluentd-docker-volumes


sudo docker run -v /home/ilegra000034/Documents/pocs/logger-app/:/var/log/application/ -it fluentd-docker-volumes
