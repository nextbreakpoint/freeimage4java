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

# Build for MacOS

We recommend to use brew for installing required tools.

Install Swig version 3.0.8: 

	brew install swig

Install command line tools for Xcode from Apple Developer Center.

Execute build script:

	sh build-macos.sh

# Build for Linux

We recommend to use docker for creating a container with required tools.

Install docker on your system (Linux or MacOS).

Execute script to create the image:

	sh create-image-linux.sh

Execute script to run a container from the image:

	sh run-image-linux.sh

# Build for Windows (cross-compilation)

We recommend to use docker for creating a container with required tools for cross-compiling using Mingw-w64.

Install docker on your system (Linux or MacOS).

Execute script to create the image:

	sh create-image-mingw64.sh

Execute script to run a container from the image:

	sh run-image-mingw64.sh
