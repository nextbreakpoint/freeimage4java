# FreeImage4Java 3.18.0-2.1

FreeImage4Java provides a Java wrapper of FreeImage library version 3.18.0.


## License

FreeImage4Java is distributed under the terms of BSD 3-Clause License.

    Copyright (c) 2016-2024, Andrea Medeghini
    All rights reserved.

    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions are met:

    * Redistributions of source code must retain the above copyright notice, this
        list of conditions and the following disclaimer.

    * Redistributions in binary form must reproduce the above copyright notice,
        this list of conditions and the following disclaimer in the documentation
        and/or other materials provided with the distribution.

    * Neither the name of FreeImage4Java nor the names of its
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


## Notice

FreeImage4Java is distributed as independent JAR files, one for each supported operating system. 
Each JAR file contains a native library which must be installed in the library path of the Java runtime.

Please note that the native library is statically liked with FreeImage library under the terms of FreeImage Public License, version 1.0.
You should have received a copy of the FIPL v1.0 license with FreeImage4Java. See http://freeimage.sourceforge.net for more information about FreeImage.


## Get binaries

FreeImage4Java is available in Maven Central Repository and [GitHub](https://github.com/nextbreakpoint/freeimage4Java).

If you are using Maven, add one of the following dependencies in your POM:

    <dependency>
        <groupId>com.nextbreakpoint</groupId>
        <artifactId>com.nextbreakpoint.freeimage4Java.macos</artifactId>
        <version>3.18.0-2.1</version>
    </dependency>
    
    <dependency>
        <groupId>com.nextbreakpoint</groupId>
        <artifactId>com.nextbreakpoint.freeimage4Java.linux</artifactId>
        <version>3.18.0-2.1</version>
    </dependency>
    
    <dependency>
        <groupId>com.nextbreakpoint</groupId>
        <artifactId>com.nextbreakpoint.freeimage4Java.windows</artifactId>
        <version>3.18.0-2.1</version>
    </dependency>

Also, add one of the following to download the native library:

    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-dependency-plugin</artifactId>
        <executions>
            <execution>
                <id>copy-lib</id>
                <phase>package</phase>
                <goals>
                    <goal>unpack</goal>
                </goals>
                <configuration>
                    <artifactItems>
                        <artifactItem>
                            <groupId>com.nextbreakpoint</groupId>
                            <artifactId>com.nextbreakpoint.freeimage4java.macos</artifactId>
                            <classifier>x86_64</classifier>
                            <type>jar</type>
                            <overWrite>true</overWrite>
                            <outputDirectory>${project.build.directory}/lib</outputDirectory>
                        </artifactItem>
                    </artifactItems>
                    <includes>**/*.dylib</includes>
                </configuration>
            </execution>
        </executions>
    </plugin>

    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-dependency-plugin</artifactId>
        <executions>
            <execution>
                <id>copy-lib</id>
                <phase>package</phase>
                <goals>
                    <goal>unpack</goal>
                </goals>
                <configuration>
                    <artifactItems>
                        <artifactItem>
                            <groupId>com.nextbreakpoint</groupId>
                            <artifactId>com.nextbreakpoint.freeimage4java.linux</artifactId>
                            <classifier>x86_64</classifier>
                            <type>jar</type>
                            <overWrite>true</overWrite>
                            <outputDirectory>${project.build.directory}/lib</outputDirectory>
                        </artifactItem>
                    </artifactItems>
                    <includes>**/*.so</includes>
                </configuration>
            </execution>
        </executions>
    </plugin>

    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-dependency-plugin</artifactId>
        <executions>
            <execution>
                <id>copy-lib</id>
                <phase>package</phase>
                <goals>
                    <goal>unpack</goal>
                </goals>
                <configuration>
                    <artifactItems>
                        <artifactItem>
                            <groupId>com.nextbreakpoint</groupId>
                            <artifactId>com.nextbreakpoint.freeimage4java.windows</artifactId>
                            <classifier>x86_64</classifier>
                            <type>jar</type>
                            <overWrite>true</overWrite>
                            <outputDirectory>${project.build.directory}/lib</outputDirectory>
                        </artifactItem>
                    </artifactItems>
                    <includes>**/*.dll</includes>
                </configuration>
            </execution>
        </executions>
    </plugin>


## Build libraries

We recommend to use a Mac to build FreeImage4Java because you can compile the library for all systems in one place, but that is not strictly required.
A Linux system can be used to compile the library for Linux and Windows (via cross compilation), and use a Mac system only to compile the library for MacOS.

We assume you have a Mac, however the same build instructions work for Linux (but only for compiling the library for Linux and Windows).

Make sue you have installed Xcode command line tools (only for MacOS), Docker, git, make, maven, and Java 22.

    We use Docker to simplify the toolchain configuration and ensure to have a consistent environment

### Build library for MacOS

Compile and install the library:

    make -C freeimage4Java-macos/native build-lib install

The library will be installed in the resources directory of the module:

    freeimage4Java-macos/src/main/resources/libfreeimage4Java.dylib

Inspect the symbols exported by the library:

    nm -gU freeimage4Java-macos/src/main/resources/libfreeimage4Java.dylib

Inspect the libraries linked with the library:

    otool -L freeimage4Java-macos/src/main/resources/libfreeimage4Java.dylib

### Build library for Linux

Compile and install the library:

    make -C freeimage4Java-linux/native build-lib install

The library will be installed in the resources directory of the module:

    freeimage4Java-linux/src/main/resources/libfreeimage4Java.so

Inspect the symbols exported by the library:

    objdump -t freeimage4Java-linux/src/main/resources/libfreeimage4Java.so

Inspect the libraries linked with the library:

    objdump -p freeimage4Java-linux/src/main/resources/libfreeimage4Java.so | grep .so

### Build library for Windows

Compile and install the library:

    make -C freeimage4Java-windows/native build-lib install

The library will be installed in the resources directory of the module:

    freeimage4Java-windows/src/main/resources/freeimage4Java.dll

Inspect the symbols exported by the library:

    objdump -t freeimage4Java-windows/src/main/resources/freeimage4Java.dll

Inspect the libraries linked with the library:

    objdump -p freeimage4Java-windows/src/main/resources/freeimage4Java.dll | grep .dll

## Build Java code

Compile and package the JARs:

    make clean package

The artifacts will be created in the target directory of each module:

    freeimage4Java-macos/target/com.nextbreakpoint.freeimage4Java.macos-3.18.0-2.1.jar
    freeimage4Java-macos/target/com.nextbreakpoint.freeimage4Java.macos-3.18.0-2.1-x86_64.jar
    
    freeimage4Java-linux/target/com.nextbreakpoint.freeimage4Java.linux-3.18.0-2.1.jar
    freeimage4Java-linux/target/com.nextbreakpoint.freeimage4Java.linux-3.18.0-2.1-x86_64.jar
    
    freeimage4Java-windows/target/com.nextbreakpoint.freeimage4Java.windows-3.18.0-2.1.jar
    freeimage4Java-windows/target/com.nextbreakpoint.freeimage4Java.windows-3.18.0-2.1-x86_64.jar

List the content of the JAR files:

    unzip -t freeimage4Java-macos/target/com.nextbreakpoint.freeimage4Java.macos-3.18.0-2.1.jar
    unzip -t freeimage4Java-macos/target/com.nextbreakpoint.freeimage4Java.macos-3.18.0-2.1-x86_64.jar

    unzip -t freeimage4Java-linux/target/com.nextbreakpoint.freeimage4Java.linux-3.18.0-2.1.jar
    unzip -t freeimage4Java-linux/target/com.nextbreakpoint.freeimage4Java.linux-3.18.0-2.1-x86_64.jar

    unzip -t freeimage4Java-windows/target/com.nextbreakpoint.freeimage4Java.windows-3.18.0-2.1.jar
    unzip -t freeimage4Java-windows/target/com.nextbreakpoint.freeimage4Java.windows-3.18.0-2.1-x86_64.jar

Install the artifacts in your local Maven repository:

    make install

## Test Java code

Test the MacOS module (must be executed on MacOS):

    make verify system=macos

Test the Linux module (must be executed on Linux):

    make verify system=linux

Test the Windows module (must be executed on Windows):

    make verify system=windows

## Generate Java code

The Java code of all modules is generated from the FreeImage header files by jextract.

Download and install jextract for Java 22 for all target systems (see links below).

Edit the source file in the target module updating the path of jextract and java.

Generate the code for MacOS (must be executed on MacOS):

    eval $(cat freeimage4Java-macos/source) && make extract system=macos

Generate the code for Linux (must be executed on Linux):

    eval $(cat freeimage4Java-linux/source) && make extract system=linux

Generate the code for Windows (must be executed on Windows):

    eval $(cat freeimage4Java-windows/source) && make extract system=windows


## Code examples

A simple example is provided for each supported system.

See POM files for details about how to download the native library and execute the code:

    freeimage4Java-macos-examples/pom.xml

    freeimage4Java-linux-examples/pom.xml

    freeimage4Java-windows-examples/pom.xml


## References

Java Foreign Function and Memory API:
https://openjdk.org/jeps/454

jextract download:
https://jdk.java.net/jextract/

jextract guide:
https://github.com/openjdk/jextract/blob/master/doc/GUIDE.md

FreeImage documentation:
https://freeimage.sourceforge.io/documentation.html

FreeImage source code:
https://sourceforge.net/p/freeimage/code/

