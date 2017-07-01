### Flink-poc

## 1) Download and run Apache Flink

First, you need to download binaries of apache flink from this url http://archive.apache.org/dist/flink/flink-1.3.1/
```
cd ~/Downloads        # Go to download directory
tar xzf flink-*.tgz   # Unpack the downloaded archive
cd flink-1.3.1

./bin/start-local.sh  # Start Flink
```
Apache Flink will run in localhost:8081 

## 2) Start local server using netcat
```
nc -l 9000
```

## 3) Build flink-poc project
Enter inside de flink-poc folder:
```
gradle build
```

## 4) Submit the Flink program
```
flink-1.3.1/bin/flink run ../repositories/big-data-sandbox/flink-poc/build/libs/com.mserpa.flink.poc-1.0-SNAPSHOT.jar --port 9000
```
## 5) Have a fun :D
You can test writing words in localserver. For example:
```
nc -l 9000
Lorem
Lorem Ipsum
```
