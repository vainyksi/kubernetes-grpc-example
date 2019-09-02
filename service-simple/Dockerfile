FROM openjdk:11-slim

COPY build/distributions/kubernetes.grpc.service.simple-1.0-SNAPSHOT.tar /kubernetes.grpc.service.simple-1.0-SNAPSHOT.tar

RUN tar xf kubernetes.grpc.service.simple-1.0-SNAPSHOT.tar
RUN rm kubernetes.grpc.service.simple-1.0-SNAPSHOT.tar

CMD sh /kubernetes.grpc.service.simple-1.0-SNAPSHOT/bin/kubernetes.grpc.service.simple \
--interface "$POD_IP" \
--port $APP_PORT
