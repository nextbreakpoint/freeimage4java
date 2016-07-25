FROM ubuntu:latest

RUN apt-get update && apt-get -y install sudo apt-utils git 

RUN apt-get update && apt-get -y install software-properties-common python-software-properties libgtk-3-dev libswt-gtk-3-java xvfb net-tools swig

RUN add-apt-repository -y ppa:webupd8team/java

RUN echo debconf shared/accepted-oracle-license-v1-1 select true | debconf-set-selections
RUN echo debconf shared/accepted-oracle-license-v1-1 seen true | debconf-set-selections

RUN apt-get update && apt-get install -y oracle-java8-installer oracle-java8-set-default

COPY freeimage4java.i freeimage4java.i 

COPY Makefile.java Makefile.java

COPY build-java.sh build-java.sh

RUN chmod 700 build-java.sh

CMD ["sh", "-c", "/build-java.sh"]
