Flink-poc

1) First, you need to download binaries of apache flink from this url http://archive.apache.org/dist/flink/flink-1.3.1/

2) Starting flink

cd ~/Downloads        # Go to download directory
tar xzf flink-*.tgz   # Unpack the downloaded archive
cd flink-1.3.1

./bin/start-local.sh  # Start Flink

Apache flink will run in localhost:8081 

3) Starting local server using netcat

nc -l 9000

4) Enter inside de flink-poc folder:
gradle build

5) Submit the Flink program

flink-1.3.1/bin/flink run ../repositories/big-data-sandbox/flink-poc/build/libs/com.mserpa.flink.poc-1.0-SNAPSHOT.jar --port 9000

6) You can test writing words in localserver. For example:
nc -l 9000
Lorem
Lorem Ipsum
