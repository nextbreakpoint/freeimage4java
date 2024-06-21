#!/bin/sh

FREEIMAGE_HOME="Z:/Documents/projects/github/freeimage4java/freeimage4java-windows/native/freeimage/Dist"
INCLUDE_PATH="C:/Users/andrea/Documents/cygwin/usr/include"

export PATH=$PATH:${JEXTRACT_HOME}/bin

jextract --dump-includes includes.txt --include-dir "${FREEIMAGE_HOME}" --include-dir "${INCLUDE_PATH}" libfreeimage.h