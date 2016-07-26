#/bin/sh
export BUILD_DIR=`pwd`/build

rm -fR FreeImage

cvs -z3 -d:pserver:anonymous@freeimage.cvs.sourceforge.net:/cvsroot/freeimage co -P FreeImage

cp Makefile.lib.macos FreeImage/Makefile.lib.macos

cp Makefile.jni.macos FreeImage/Makefile.jni.macos

cp macos.patch FreeImage/macos.patch

patch -p0 < FreeImage/macos.patch

cd FreeImage

make -f Makefile.lib.macos FreeImage

make -f Makefile.jni.macos all
