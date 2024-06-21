#!/bin/sh

mkdir -p "$(pwd)/build" && docker run --name freeimage4java-build --rm -i --volume="$(pwd)/build:/build" .
