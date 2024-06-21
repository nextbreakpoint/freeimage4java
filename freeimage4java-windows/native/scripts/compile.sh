#!/bin/sh

set -e

FREEIMAGE_HOME=$(pwd)/freeimage

LIB_TYPE=64

cd "$FREEIMAGE_HOME" || exit

git reset --hard
patch -p1 -N < ../freeimage.patch
cp ../Makefile.mingw64 "$FREEIMAGE_HOME"

make -f Makefile.mingw64

x86_64-w64-mingw32-gcc --verbose \
  -o freeimage4java.dll \
  -shared \
  -m${LIB_TYPE} \
  -Wall \
  -fpic \
  -Wl,--out-implib=freeimage4java.dll.a \
  -Wl,-add-stdcall-alias \
  -Wl,-Bsymbolic \
  -Wl,--whole-archive \
  libfreeimage.a \
  -Wl,--no-whole-archive \
  -Wl,-Bstatic \
  -lm \
  -lstdc++ \
  -lws2_32