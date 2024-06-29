#!/bin/sh

set -e

FREEIMAGE_HOME=$(pwd)/freeimage

if [ -d "$FREEIMAGE_HOME" ]; then
  make -C "$FREEIMAGE_HOME" clean
fi
