mkdir -p `pwd`/dist

docker run --name freeimage4java-mingw64 --rm -i --volume=`pwd`/dist:/dist freeimage4java-mingw64