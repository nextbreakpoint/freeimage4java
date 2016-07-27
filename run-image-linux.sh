#/bin/sh

mkdir -p `pwd`/build && docker run --name freeimage4java-linux --rm -i --volume=`pwd`/build:/build freeimage4java-linux
