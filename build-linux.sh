#/bin/sh
export BUILD_DIR=`pwd`/build

cd FreeImage

make -f Makefile.lib.linux FreeImage

make -f Makefile.jni.linux all
