#!/bin/sh

set -e

FREEIMAGE_HOME=$(pwd)/freeimage

LIB_TYPE=64

cd "$FREEIMAGE_HOME" || exit

git reset --hard
patch -p1 -N < ../freeimage.patch

make -f Makefile.mingw

x86_64-w64-mingw32-gcc --verbose \
  -o freeimage4java.dll \
  -shared \
  -m${LIB_TYPE} \
  -Wall \
  -fpic \
  -static-libgcc \
  -static-libstdc++ \
  -Wl,--out-implib=freeimage4java.dll.a \
  -Wl,-add-stdcall-alias \
  -Wl,--no-undefined \
  -Wl,-Bsymbolic \
  -Wl,--whole-archive \
  libfreeimage.a \
  -Wl,--no-whole-archive \
  -Wl,-Bstatic \
  -lm \
  -lstdc++ \
  -lws2_32