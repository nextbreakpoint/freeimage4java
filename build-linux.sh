#/bin/sh
export BUILD_DIR=`pwd`/build

cd FreeImage

export DISTDIR=$BUILD_DIR/linux
make -f Makefile.lib.linux all

make -f Makefile.jni.linux all
