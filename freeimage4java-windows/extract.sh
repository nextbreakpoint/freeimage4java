#!/bin/sh

FREEIMAGE_HOME="$(cygpath -w $(pwd)/native/freeimage/Dist)"
INCLUDE_PATH="$(cygpath -w $CYGWIN_HOME/usr/include)"

export PATH=$PATH:${JEXTRACT_HOME}/bin

jextract @includes.txt \
  --output src/main/java \
  --target-package com.nextbreakpoint.freeimage4java.windows \
  --include-dir "${FREEIMAGE_HOME}" \
  --include-dir "${INCLUDE_PATH}" \
  --use-system-load-library \
  --library freeimage4java \
  --header-class-name Libfreeimage \
  libfreeimage.h