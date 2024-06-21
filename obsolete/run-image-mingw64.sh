#/bin/sh

mkdir -p `pwd`/build && docker run --name freeimage4java-mingw64 --rm -i --volume=`pwd`/build:/build freeimage4java-mingw64