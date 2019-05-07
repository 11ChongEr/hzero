echo ">>> git pull"
git pull

echo ">>> mvn clean package"
mvn clean package

echo ">>> cd target"
cd target

JAR=service_name.jar
MPORT=8001

echo ">>> kill -9 $(jps -ml | grep $JAR | awk '{print $1}')"
kill -9 $(jps -ml | grep $JAR | awk '{print $1}')

echo ">>> kill -9 $(lsof -n -P -t -i:$MPORT)"
kill -9 $(lsof -n -P -t -i:$MPORT)

mv app.jar $JAR

echo ">>> nohup java -jar -Xms512m -Xmx1024m $JAR >app.log &"
BUILD_ID=dontKillMe nohup java -jar -Xms512m -Xmx1024m $JAR >app.log &