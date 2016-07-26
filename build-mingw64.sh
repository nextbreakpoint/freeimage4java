#/bin/sh
export BUILD_DIR=`pwd`/build

cd FreeImage

make -f Makefile.lib.mingw64 FreeImage

make -f Makefile.jni.mingw64 all
