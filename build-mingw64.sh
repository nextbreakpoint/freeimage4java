#/bin/sh
export BUILD_DIR=`pwd`/build

patch -p0 < Patch.mingw64

cd FreeImage

make -f Makefile.lib.mingw64 FreeImage
make -f Makefile.jni.mingw64 all
