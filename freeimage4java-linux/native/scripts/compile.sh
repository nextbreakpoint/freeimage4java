#!/bin/sh

set -e

FREEIMAGE_HOME=$(pwd)/freeimage

LIB_TYPE=64

cd "$FREEIMAGE_HOME" || exit

git reset --hard
patch -p1 -N < ../freeimage.patch

make -f Makefile.gnu

gcc --verbose \
  -o libfreeimage4java.so \
  -shared \
  -m${LIB_TYPE} \
  -Wall \
  -fpic \
  -Wl,--no-undefined \
  -Wl,-Bsymbolic \
  -Wl,--whole-archive \
  libfreeimage.a \
  -Wl,--no-whole-archive \
  -lm \
  -lstdc++
