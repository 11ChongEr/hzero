FROM registry.cn-hangzhou.aliyuncs.com/choerodon-tools/javabase:0.7.1
COPY app.jar /hzero-register.jar
ENTRYPOINT exec java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap $JAVA_OPTS $SKYWALKING_OPTS  -jar /hzero-register.jar

