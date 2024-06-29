#!/bin/bash

set -e

FREEIMAGE_HOME=$(pwd)/freeimage

if [ ! -d "$FREEIMAGE_HOME" ]; then
  git clone https://github.com/nextbreakpoint/FreeImage.git "$FREEIMAGE_HOME"
else
  pushd "$FREEIMAGE_HOME"
  git fetch
  popd
fi

pushd "$FREEIMAGE_HOME"
git checkout ee5b759719c5d3f8dd62ac0a71129a14cab5b0cd
popd
