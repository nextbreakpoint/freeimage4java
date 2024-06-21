#!/bin/sh

set -e

test ! -d "$(pwd)/freeimage" && git clone https://github.com/nextbreakpoint/FreeImage.git "$(pwd)/freeimage"

cd "$(pwd)/freeimage" && git checkout ee5b759719c5d3f8dd62ac0a71129a14cab5b0cd
