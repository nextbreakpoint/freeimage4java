# freeimage4java

FreeImage for Java provides a JNI wrapper of FreeImage library version 3.18.0.


## License

FreeImage for Java is distributed under the terms of BSD 3-Clause License.

    Copyright (c) 2016, Andrea Medeghini
    All rights reserved.
    
    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions are met:
    
    * Redistributions of source code must retain the above copyright notice, this
      list of conditions and the following disclaimer.
    
    * Redistributions in binary form must reproduce the above copyright notice,
      this list of conditions and the following disclaimer in the documentation
      and/or other materials provided with the distribution.
    
    * Neither the name of FreeImage for Java (or freeimage4java) nor the names of its
      contributors may be used to endorse or promote products derived from
      this software without specific prior written permission.
    
    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
    AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
    IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
    DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
    FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
    DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
    SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
    CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
    OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
    OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.


## Generate JNI library's code

We use docker for generating JNI library's code.

  Before you continue install docker on your system.

Execute script to create base image:

  sh create-image-base.sh

Execute script to create image for generating code:

  sh create-image-java.sh

Execute script to run a container from the image:

  sh run-image-java.sh

The output will be produced in directory build/freeimage4java/java.


## Build library for MacOS

Download and install command line tools from Apple Developer Center.

  Generate wrapper's code before compiling library for MacOS.

Execute build script:

	sh build-macos.sh

The native library will be created in directory build/freeimage4java/macos.


## Build library for Linux

We use docker for compiling Linux library.

  Generate wrapper's code before compiling library for Linux.

Execute script to create image for building library:

	sh create-image-linux.sh

Execute script to run a container from the image:

	sh run-image-linux.sh

The native library will be created in directory build/freeimage4java/linux.


## Build library for Windows (cross-compilation)

We use docker for cross-compiling Windows library using Mingw-w64.

  Generate wrapper's code before compiling library for Windows.

Execute script to create image for building library:

	sh create-image-mingw64.sh

Execute script to run a container from the image:

	sh run-image-mingw64.sh

The native library will be created in directory build/freeimage4java/win32.


## Create artifacts 

We use Apache Maven to compile Java code and package the artifacts.

  Generate wrapper's code and build libraries before creating artifacts.

Execute script to create artifacts:

  sh package.sh

The packages will be created in directory build/target

Execute script to install artifacts:

  sh install.sh

The artifacts will be created in target subdirectories of build/freeimage4java.


## Run tests

Execute script to run tests:

  sh run-tests.sh
