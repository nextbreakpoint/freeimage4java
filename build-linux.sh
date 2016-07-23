#/bin/sh
export DIST_DIR=/dist/linux

cd FreeImage

make -f Makefile.lib.linux all
make -f Makefile.jni.linux all
