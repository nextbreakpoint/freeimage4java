.PHONY: clean
clean:
	mvn clean

.PHONY: compile
compile:
	mvn compile

.PHONY: package
package:
	mvn package -DskipTests=true

.PHONY: install
install:
	mvn install -DskipTests=true

.PHONY: extract
extract:
	test $(system)
	@echo System = $(system)
	cd freeimage4java-$(system) && ./extract.sh

.PHONY: dump
dump:
	test $(system)
	@echo System = $(system)
	cd freeimage4java-$(system) && ./dump.sh

.PHONY: verify
verify:
	test $(system)
	@echo System = $(system)
	#MAVEN_OPTS="--enable-preview --enable-native-access=ALL-UNNAMED -Djava.library.path=freeimage4java-tests/target/lib -Djextract.trace.downcalls=false -Xlog:library" mvn verify -P$(system);
	MAVEN_OPTS="--enable-preview --enable-native-access=ALL-UNNAMED -Djava.library.path=freeimage4java-tests/target/lib -Djextract.trace.downcalls=false $(properties)" mvn verify -P$(system);

.PHONY: copy-legal
copy-legal:
	cp legal/*.txt freeimage4java-macos/src/main/resources/META-INF/
	cp legal/*.txt freeimage4java-linux/src/main/resources/META-INF/
	cp legal/*.txt freeimage4java-windows/src/main/resources/META-INF/

.PHONY: dist
dist:
	rm -fR dist
	mkdir -p dist
	cp freeimage4java-macos/target/com.nextbreakpoint.freeimage4java.macos-*.jar dist
	cp freeimage4java-linux/target/com.nextbreakpoint.freeimage4java.linux-*.jar dist
	cp freeimage4java-windows/target/com.nextbreakpoint.freeimage4java.windows-*.jar dist

.PHONY: github-release
github-release:
	mvn -Dchannel=github clean package de.jutzig:github-release-plugin:release -Dgithub.draft=true

.PHONY: maven-release
maven-release:
	mvn -Dchannel=ossrh clean deploy

.PHONY: set-version
set-version:
	test $(version)
	mvn versions:set -DnewVersion=$(version)
	mvn versions:commit
