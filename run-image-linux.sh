mkdir -p dist/linux

docker run --name freeimage4java-linux --rm -i --volume=dist:/dist freeimage4java-linux
