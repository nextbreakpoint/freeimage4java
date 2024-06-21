#/bin/sh
export BUILD_DIR=`pwd`/build

mkdir -p $BUILD_DIR/com.nextbreakpoint.freeimage4java

cp maven/com.nextbreakpoint.freeimage4java/pom.xml $BUILD_DIR/com.nextbreakpoint.freeimage4java

cd $BUILD_DIR/com.nextbreakpoint.freeimage4java

mvn clean install
