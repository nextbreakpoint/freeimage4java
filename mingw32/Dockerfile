FROM ubuntu:latest

RUN apt-get update && apt-get -y install sudo nano git cvs make mingw-w64 swig

RUN apt-get update && apt-get install -y git software-properties-common python-software-properties libgtk-3-dev libswt-gtk-3-java xvfb net-tools

RUN add-apt-repository -y ppa:webupd8team/java

RUN echo debconf shared/accepted-oracle-license-v1-1 select true | debconf-set-selections
RUN echo debconf shared/accepted-oracle-license-v1-1 seen true | debconf-set-selections

RUN apt-get update && apt-get install -y oracle-java8-installer oracle-java8-set-default

RUN cvs -z3 -d:pserver:anonymous@freeimage.cvs.sourceforge.net:/cvsroot/freeimage co -P FreeImage

COPY freeimage.patch FreeImage/freeimage.patch
COPY Makefile.mingw64 FreeImage/Makefile.mingw64
COPY Makefile.jni.mingw64 FreeImage/Makefile.jni.mingw64

COPY ../freeimage4java.i FreeImage/freeimage4java.i 

RUN patch -p0 < FreeImage/freeimage.patch

RUN mkdir /build

#CMD ["bash", "-c", "cd /FreeImage && make -f Makefile.mingw64 all"]

CMD ["bash"]
