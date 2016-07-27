#/bin/sh
export BUILD_DIR=`pwd`/build

mkdir -p $BUILD_DIR/freeimage4java

cd $BUILD_DIR/freeimage4java

mvn clean install
