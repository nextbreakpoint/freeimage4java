#!/bin/sh

FREEIMAGE_HOME=$(pwd)/native/freeimage/Dist

export PATH=$PATH:${JEXTRACT_HOME}/bin

jextract --dump-includes includes.txt \
  --include-dir "${FREEIMAGE_HOME}" \
  libfreeimage.h