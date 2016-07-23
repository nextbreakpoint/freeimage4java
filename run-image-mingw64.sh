mkdir -p build/win32
mkdir -p dist/win32

docker run --name freeimage4java-mingw64 --rm -i --volume=build/win32:/build --volume=dist/win32:/dist freeimage4java-mingw64