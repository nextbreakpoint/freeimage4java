#!/bin/sh

set -e

FREEIMAGE_HOME=$(pwd)/freeimage

LIB_TYPE=64

cd "$FREEIMAGE_HOME" || exit

git reset --hard
patch -p1 -N < ../freeimage.patch

make

gcc --verbose \
  -o libfreeimage4java.dylib \
  -dynamiclib \
  -m${LIB_TYPE} \
  -Wall \
  -fpic \
  -Wl,-force_load \
  libfreeimage.a \
  -lm \
  -lstdc++
