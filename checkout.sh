#/bin/sh

#cvs -z3 -d:pserver:anonymous@freeimage.cvs.sourceforge.net:/cvsroot/freeimage co -P FreeImage

git clone https://github.com/nextbreakpoint/FreeImage.git FreeImage

cd FreeImage && git checkout 02761c9e7da266c9a0045790d502449f745e576d
