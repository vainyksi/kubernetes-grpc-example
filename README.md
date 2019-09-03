# kubernetes-grpc-example

Repository contains gRPC example server with deployment on kubernetes.

There is a gRPC service defined in ```grpc-proto/src/main/proto/simple-service.proto``` file.
Simple-service module implements the service defined in ```.proto``` file and exposes it through gRPC server.
The gRPC service implementation just responds to the gRPC requests with static response message:
```
{
  "primaryInfo": "hello world",
  "otherInfo": "this is secondary info"
}
```

## build

```
gradle clean build
```

## run

```
java -jar build/libs/kubernetes.grpc.service.simple-1.0-SNAPSHOT.jar [--interface "$POD_IP"] [--port $APP_PORT]

# default attributes are:
#     interface    0.0.0.0
#     port         8989 
```

## test

Use grpcurl tool or other gRPC client to test simple-service.
(The service in this example runs on endpoint 192.168.1.240:8989)

```
grpcurl -plaintext -v -import-path grpc-proto/src/main/proto -proto simple-service.proto 192.168.1.240:8989 backend.SimpleService/getInfo
```

should return:

```
Resolved method descriptor:
rpc getInfo ( .google.protobuf.Empty ) returns ( .backend.InfoResponse );

Request metadata to send:
(empty)

Response headers received:
content-type: application/grpc
grpc-accept-encoding: gzip

Response contents:
{
  "primaryInfo": "hello world",
  "otherInfo": "this is secondary info"
}

Response trailers received:
(empty)
Sent 0 requests and received 1 response
```

## Kubernetes deploy

It is assumed that Kubernetes cluster is up and running.

### prepare simple-service docker image

Wrap simple-service app into docker image - build simple-service as docker image:

```
./kubernetes/build_docker_images.sh
```

### deploy docker image to kubernetes image registry

This example uses [microk8s.io]() as a Kubernetes environment, so the deploy images to docker image repository used with
 other kubernetes environment may vary. The goal is to provide the docker image to all kubernetes nodes as the nodes can
 run containers from this image.

To deploy simple-service docker image to microk8s run

```
./kubernetes/deploy_docker_images_microk8s.sh
```

### apply kubernetes deployment

Run the simple-service in kubernetes

```
kubectl apply -f kubernetes/simple-service-deployment.yaml
```
