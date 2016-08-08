#/bin/sh
export BUILD_DIR=`pwd`/build

patch -p0 < Patch.linux

cd FreeImage

make -f Makefile.lib.linux FreeImage
make -f Makefile.jni.linux all
