#!/bin/bash

# helper script to upload docker images to local microk8s into namespace k8s.io

ORIGIN_LOCATION=$PWD
SCRIPT_LOCATION=$(dirname "$0")

cd "$SCRIPT_LOCATION"

for DOCKER_IMAGE in simple-service:v1.0.0
do
  echo ">>>>>>> loading Docker image" $DOCKER_IMAGE "into local microk8s"
  docker save --output=$DOCKER_IMAGE.tar $DOCKER_IMAGE

  microk8s.ctr -n k8s.io image import $DOCKER_IMAGE.tar

  rm $DOCKER_IMAGE.tar

done

cd $ORIGIN_LOCATION
