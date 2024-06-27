package com.nextbreakpoint.freeimage4java.macos;

import java.lang.foreign.Arena;

import static com.nextbreakpoint.freeimage4java.macos.Libfreeimage.FIF_PNG;
import static com.nextbreakpoint.freeimage4java.macos.Libfreeimage.FreeImage_ConvertToGreyscale;
import static com.nextbreakpoint.freeimage4java.macos.Libfreeimage.FreeImage_Initialise;
import static com.nextbreakpoint.freeimage4java.macos.Libfreeimage.FreeImage_Load;
import static com.nextbreakpoint.freeimage4java.macos.Libfreeimage.FreeImage_Save;
import static com.nextbreakpoint.freeimage4java.macos.Libfreeimage.FreeImage_Unload;
import static com.nextbreakpoint.freeimage4java.macos.Libfreeimage.PNG_DEFAULT;
import static com.nextbreakpoint.freeimage4java.macos.Libfreeimage.TRUE;
import static java.lang.foreign.MemorySegment.NULL;

class ConvertToGreyscaleMain {
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        final String sourceFileName = args[0];
        final String outputFileName = args[1];

        System.out.println("Converting image to greyscale...");

        System.out.println(STR."Source file \{sourceFileName}");
        System.out.println(STR."Output file \{outputFileName}");

        try (var arena = Arena.ofConfined()) {
            var pSourceBitmap = NULL;
            var pOutputBitmap = NULL;

            try {
                FreeImage_Initialise(TRUE());

                var pInputFileName = arena.allocateFrom(sourceFileName);
                var pOutputFileName = arena.allocateFrom(outputFileName);

                pSourceBitmap = FreeImage_Load(FIF_PNG(), pInputFileName, PNG_DEFAULT());

                pOutputBitmap = FreeImage_ConvertToGreyscale(pSourceBitmap);

                int result = FreeImage_Save(FIF_PNG(), pOutputBitmap, pOutputFileName, PNG_DEFAULT());

                if (result == TRUE()) {
                    System.out.println("Image converted");
                } else {
                    throw new IllegalArgumentException("Failed to process image");
                }
            }
            finally {
                if (!pSourceBitmap.equals(NULL)) {
                    FreeImage_Unload(pSourceBitmap);
                }

                if (!pOutputBitmap.equals(NULL)) {
                    FreeImage_Unload(pOutputBitmap);
                }
            }
        }
    }
}