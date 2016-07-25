#/bin/sh
export BUILD_DIR=`pwd`/build

rm -fR FreeImage

cvs -z3 -d:pserver:anonymous@freeimage.cvs.sourceforge.net:/cvsroot/freeimage co -P FreeImage

cp Makefile.lib.macos FreeImage/

cp Makefile.jni.macos FreeImage/

patch -p0 < macos.patch

cd FreeImage

export DISTDIR=$BUILD_DIR/macos
make -f Makefile.lib.macos all

make -f Makefile.jni.macos all
