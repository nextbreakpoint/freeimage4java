#/bin/sh
export BUILD_DIR=`pwd`/build

rm -fR FreeImage

sh checkout.sh

cp Makefile.lib.macos FreeImage/Makefile.lib.macos
cp Makefile.jni.macos FreeImage/Makefile.jni.macos

patch -p0 < Patch.macos

cd FreeImage

make -f Makefile.lib.macos FreeImage
make -f Makefile.jni.macos all
