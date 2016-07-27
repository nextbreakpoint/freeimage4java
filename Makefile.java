BUILD_DIR?=Build
OUTPUT_DIR=$(BUILD_DIR)/freeimage4java

all:
	mkdir -p $(OUTPUT_DIR)/java/src/main/java/com/nextbreakpoint/freeimage4java && swig -v -c++ -java -o $(BUILD_DIR)/freeimage4java_wrap.cxx -outdir $(OUTPUT_DIR)/java/src/main/java/com/nextbreakpoint/freeimage4java -package com.nextbreakpoint.freeimage4java freeimage4java.i
