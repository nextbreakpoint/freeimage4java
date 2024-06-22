#!/bin/sh

set -e

FREEIMAGE_HOME=$(pwd)/freeimage

cd "$FREEIMAGE_HOME" || exit

make -f Makefile.mingw clean
