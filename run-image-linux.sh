mkdir -p build/linux
mkdir -p dist/linux

docker run --name freeimage4java-linux --rm -i --volume=build/linux:/build --volume=dist/linux:/dist freeimage4java-linux
