export BUILD_DIR=`pwd`/build

mkdir -p $BUILD_DIR/freeimage4java
mkdir -p $BUILD_DIR/freeimage4java/java
mkdir -p $BUILD_DIR/freeimage4java/linux
mkdir -p $BUILD_DIR/freeimage4java/macos
mkdir -p $BUILD_DIR/freeimage4java/win32

cp POMs/pom.xml $BUILD_DIR/freeimage4java
cp POMs/java/pom.xml $BUILD_DIR/freeimage4java/java
cp POMs/linux/pom.xml $BUILD_DIR/freeimage4java/linux
cp POMs/macos/pom.xml $BUILD_DIR/freeimage4java/macos
cp POMs/win32/pom.xml $BUILD_DIR/freeimage4java/win32

mkdir -p `pwd`/build && docker run --name freeimage4java-java --rm -i --volume=`pwd`/build:/build freeimage4java-java
