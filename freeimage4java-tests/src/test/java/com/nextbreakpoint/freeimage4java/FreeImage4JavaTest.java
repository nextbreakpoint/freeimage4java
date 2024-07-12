package com.nextbreakpoint.freeimage4java;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static com.nextbreakpoint.freeimage4java.Libfreeimage.FIF_PNG;
import static com.nextbreakpoint.freeimage4java.Libfreeimage.FreeImage_Allocate;
import static com.nextbreakpoint.freeimage4java.Libfreeimage.FreeImage_GetPixelColor;
import static com.nextbreakpoint.freeimage4java.Libfreeimage.FreeImage_Initialise;
import static com.nextbreakpoint.freeimage4java.Libfreeimage.FreeImage_Load;
import static com.nextbreakpoint.freeimage4java.Libfreeimage.FreeImage_Save;
import static com.nextbreakpoint.freeimage4java.Libfreeimage.FreeImage_SetPixelColor;
import static com.nextbreakpoint.freeimage4java.Libfreeimage.FreeImage_Unload;
import static com.nextbreakpoint.freeimage4java.Libfreeimage.PNG_DEFAULT;
import static com.nextbreakpoint.freeimage4java.Libfreeimage.TRUE;
import static java.lang.foreign.MemorySegment.NULL;
import static org.assertj.core.api.Assertions.assertThat;

class FreeImage4JavaTest {
    private static final int IMAGE_WIDTH = 256;
    private static final int IMAGE_HEIGHT = 256;

    private static byte[] pixels;

    @BeforeAll
    public static void setup() {
        FreeImage_Initialise(TRUE());
        pixels = createPixels(IMAGE_WIDTH, IMAGE_HEIGHT);
    }

    @Test
    public void shouldLoadImage() {
        try (var arena = Arena.ofConfined()) {
            var pBitmap = NULL;
            try {
                var inputFileName = arena.allocateFrom(getFileName("sample.png"));
                pBitmap = FreeImage_Load(FIF_PNG(), inputFileName, PNG_DEFAULT());
                assertThat(pBitmap).isNotNull();
                assertThat(pBitmap.address()).isNotZero();
                assertThat(comparePixels(arena, pBitmap, pixels, IMAGE_WIDTH, IMAGE_HEIGHT)).isZero();
                var pOutputFileName = arena.allocateFrom(getFileName("test0.png"));
                int result = FreeImage_Save(FIF_PNG(), pBitmap, pOutputFileName, PNG_DEFAULT());
                assertThat(result).isEqualTo(1);
            }
            finally {
                if (!pBitmap.equals(NULL)) {
                    FreeImage_Unload(pBitmap);
                }
            }
        }
    }

    @Test
    public void shouldSaveImage() {
        try (var arena = Arena.ofConfined()) {
            var pBitmap = NULL;
            try {
                pBitmap = FreeImage_Allocate(IMAGE_WIDTH, IMAGE_HEIGHT, 32, 0x00FF0000, 0x0000FF00, 0x000000FF);
                assertThat(pBitmap).isNotNull();
                assertThat(pBitmap.address()).isNotZero();
                setImagePixels(arena, pBitmap, pixels, IMAGE_WIDTH, IMAGE_HEIGHT);
                var pOutputFileName = arena.allocateFrom(getFileName("test1.png"));
                int result = FreeImage_Save(FIF_PNG(), pBitmap, pOutputFileName, PNG_DEFAULT());
                assertThat(result).isEqualTo(1);
            } finally {
                if (!pBitmap.equals(NULL)) {
                    FreeImage_Unload(pBitmap);
                }
            }
        }
    }

    @Test
    public void shouldSaveAndLoadSameImage() {
        try (var arena = Arena.ofConfined()) {
            var pSourceBitmap = NULL;
            var pOutputBitmap = NULL;
            try {
                pSourceBitmap = FreeImage_Allocate(IMAGE_WIDTH, IMAGE_HEIGHT, 32, 0x00FF0000, 0x0000FF00, 0x000000FF);
                assertThat(pSourceBitmap).isNotNull();
                assertThat(pSourceBitmap.address()).isNotZero();
                setImagePixels(arena, pSourceBitmap, pixels, IMAGE_WIDTH, IMAGE_HEIGHT);
                var pOutputFileName = arena.allocateFrom(getFileName("test2.png"));
                FreeImage_Save(FIF_PNG(), pSourceBitmap, pOutputFileName, PNG_DEFAULT());
                pOutputBitmap = FreeImage_Load(FIF_PNG(), pOutputFileName, PNG_DEFAULT());
                assertThat(pOutputBitmap).isNotNull();
                assertThat(pOutputBitmap.address()).isNotZero();
                assertThat(comparePixels(arena, pOutputBitmap, pSourceBitmap, IMAGE_WIDTH, IMAGE_HEIGHT)).isZero();
            } finally {
                if (!pSourceBitmap.equals(NULL)) {
                    FreeImage_Unload(pSourceBitmap);
                }
                if (!pOutputBitmap.equals(NULL)) {
                    FreeImage_Unload(pOutputBitmap);
                }
            }
        }
    }

    private int comparePixels(Arena arena, MemorySegment dib1, MemorySegment dib2, int imageWidth, int imageHeight) {
        MemorySegment value1 = arena.allocate(tagRGBQUAD.layout());
        MemorySegment value2 = arena.allocate(tagRGBQUAD.layout());
        int result = 0;
        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                FreeImage_GetPixelColor(dib1, x, y, value1);
                FreeImage_GetPixelColor(dib2, x, y, value2);
                if (tagRGBQUAD.rgbRed(value2) != tagRGBQUAD.rgbRed(value1)) {
                    result += 1;
                } else if (tagRGBQUAD.rgbGreen(value2) != tagRGBQUAD.rgbGreen(value1)) {
                    result += 1;
                } else if (tagRGBQUAD.rgbBlue(value2) != tagRGBQUAD.rgbBlue(value1)) {
                    result += 1;
                } else if (tagRGBQUAD.rgbReserved(value2) != tagRGBQUAD.rgbReserved(value1)) {
                    result += 1;
                }
            }
        }
        return result;
    }

    private int comparePixels(Arena arena, MemorySegment dib, byte[] pixels, int imageWidth, int imageHeight) {
        MemorySegment value = arena.allocate(tagRGBQUAD.layout());
        int result = 0;
        for (int y = 0; y < imageHeight; y++) {
            int j = y * imageWidth;
            for (int x = 0; x < imageWidth; x++) {
                int i = (j + x) * 4;
                FreeImage_GetPixelColor(dib, x, y, value);
                if (tagRGBQUAD.rgbRed(value) != pixels[i + 0]) {
                    result += 1;
                } else if (tagRGBQUAD.rgbGreen(value) != pixels[i + 1]) {
                    result += 1;
                } else if (tagRGBQUAD.rgbBlue(value) != pixels[i + 2]) {
                    result += 1;
                } else if (tagRGBQUAD.rgbReserved(value) != pixels[i + 3]) {
                    result += 1;
                }
            }
        }
        return result;
    }

    private void setImagePixels(Arena arena, MemorySegment dib, byte[] pixels, int imageWidth, int imageHeight) {
        MemorySegment value = arena.allocate(tagRGBQUAD.layout());
        for (int y = 0; y < imageHeight; y++) {
            int j = y * imageWidth;
            for (int x = 0; x < imageWidth; x++) {
                int i = (j + x) * 4;
                tagRGBQUAD.rgbRed(value, (pixels[i + 0]));
                tagRGBQUAD.rgbGreen(value, (pixels[i + 1]));
                tagRGBQUAD.rgbBlue(value, (pixels[i + 2]));
                tagRGBQUAD.rgbReserved(value, (pixels[i + 3]));
                FreeImage_SetPixelColor(dib, x, y, value);
            }
        }
    }

    private static byte[] createPixels(int imageWidth, int imageHeight) {
        byte[] pixels = new byte[imageHeight * imageWidth * 4];
        for (int y = 0; y < imageHeight; y++) {
            int j = y * imageWidth;
            for (int x = 0; x < imageWidth; x++) {
                int i = (j + x) * 4;
                pixels[i + 0] = (byte)x;
                pixels[i + 1] = (byte)y;
                pixels[i + 2] = (byte)(x+y);
                pixels[i + 3] = y < imageHeight / 2 ? (byte)255 : (byte)50;
            }
        }
        return pixels;
    }

    private static String getFileName(String file) {
        return "%s/%s".formatted(System.getProperty("basedir", "."), file);
    }
}