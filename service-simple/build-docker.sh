#!/bin/bash

ORIGIN_LOCATION=$PWD
SCRIPT_LOCATION=$(dirname "$0")

cd "$SCRIPT_LOCATION"

docker build -t simple-service:v1.0.0 .

cd "$ORIGIN_LOCATION"
