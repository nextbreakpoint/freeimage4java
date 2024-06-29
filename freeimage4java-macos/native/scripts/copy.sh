#!/bin/sh

set -e

mkdir -p "$1" && cp freeimage/libfreeimage4java.dylib "$1"
