#!/bin/bash

set -e

if [ ! -d "$(pwd)/freeimage" ]; then
  git clone https://github.com/nextbreakpoint/FreeImage.git "$(pwd)/freeimage"
else
  pushd "$(pwd)/freeimage"
  git fetch
  popd
fi

pushd "$(pwd)/freeimage"
git checkout ee5b759719c5d3f8dd62ac0a71129a14cab5b0cd
popd
