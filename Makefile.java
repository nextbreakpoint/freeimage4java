BUILD_DIR?=Build

all:
	mkdir -p $(BUILD_DIR)/java/src/com/nextbreakpoint/freeimage4java && swig -v -c++ -java -o $(BUILD_DIR)/freeimage4java_wrap.cxx -outdir $(BUILD_DIR)/java/src/com/nextbreakpoint/freeimage4java -package com.nextbreakpoint.freeimage4java freeimage4java.i