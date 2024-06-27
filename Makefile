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

.PHONY: verify
verify:
	test $(system)
	@echo System = $(system)
	#MAVEN_OPTS="--enable-preview --enable-native-access=ALL-UNNAMED -Djava.library.path=freeimage4java-$(system)-test/target/lib -Djextract.trace.downcalls=false -Xlog:library" mvn verify -P$(system);
	MAVEN_OPTS="--enable-preview --enable-native-access=ALL-UNNAMED -Djava.library.path=freeimage4java-$(system)-test/target/lib -Djextract.trace.downcalls=false $(properties)" mvn verify -P$(system);

