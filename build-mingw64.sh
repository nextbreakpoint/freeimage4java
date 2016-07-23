#/bin/sh
export DIST_DIR=/dist/win32

cd FreeImage

make -f Makefile.lib.mingw64 all
make -f Makefile.jni.mingw64 all
