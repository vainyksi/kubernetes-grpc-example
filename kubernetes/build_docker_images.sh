#!/bin/bash

ORIGIN_LOCATION=$PWD
SCRIPT_LOCATION=$(dirname "$0")

cd "$SCRIPT_LOCATION"

../service-simple/build-docker.sh

cd $ORIGIN_LOCATION
