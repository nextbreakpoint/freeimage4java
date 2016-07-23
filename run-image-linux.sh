mkdir -p `pwd`/dist

docker run --name freeimage4java-linux --rm -i --volume=`pwd`/dist:/dist freeimage4java-linux
