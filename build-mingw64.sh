#/bin/sh
export BUILD_DIR=`pwd`/build

cd FreeImage

export DISTDIR=$BUILD_DIR/win32
make -f Makefile.lib.mingw64 all

make -f Makefile.jni.mingw64 all
