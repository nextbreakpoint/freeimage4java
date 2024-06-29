#!/bin/sh

FREEIMAGE_HOME="$(cygpath -w $(pwd)/native/freeimage/Dist)"
INCLUDE_PATH="$(cygpath -w $CYGWIN_HOME/usr/include)"

export PATH=$PATH:${JEXTRACT_HOME}/bin

jextract --dump-includes includes.txt \
  --include-dir "${FREEIMAGE_HOME}" \
  --include-dir "${INCLUDE_PATH}" \
  libfreeimage.h