#!/bin/sh

FREEIMAGE_HOME=$(pwd)/native/freeimage/Dist

export PATH=$PATH:${JEXTRACT_HOME}/bin

jextract @includes.txt \
  --output src/main/java \
  --target-package com.nextbreakpoint.freeimage4java \
  --include-dir "${FREEIMAGE_HOME}" \
  --use-system-load-library \
  --library freeimage4java \
  --header-class-name Libfreeimage \
  libfreeimage.h