#/bin/sh
export BUILD_DIR=`pwd`/build

cp pom.xml $BUILD_DIR/java/pom.xml

cd $BUILD_DIR/java

mvn verify
