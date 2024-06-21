package com.nextbreakpoint.freeimage4java.linux;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static com.nextbreakpoint.freeimage4java.linux.Libfreeimage.FIF_PNG;
import static com.nextbreakpoint.freeimage4java.linux.Libfreeimage.FreeImage_Allocate;
import static com.nextbreakpoint.freeimage4java.linux.Libfreeimage.FreeImage_GetPixelColor;
import static com.nextbreakpoint.freeimage4java.linux.Libfreeimage.FreeImage_Initialise;
import static com.nextbreakpoint.freeimage4java.linux.Libfreeimage.FreeImage_Load;
import static com.nextbreakpoint.freeimage4java.linux.Libfreeimage.FreeImage_Save;
import static com.nextbreakpoint.freeimage4java.linux.Libfreeimage.FreeImage_SetPixelColor;
import static com.nextbreakpoint.freeimage4java.linux.Libfreeimage.FreeImage_Unload;
import static com.nextbreakpoint.freeimage4java.linux.Libfreeimage.PNG_DEFAULT;
import static com.nextbreakpoint.freeimage4java.linux.Libfreeimage.TRUE;
import static java.lang.foreign.MemorySegment.NULL;
import static org.assertj.core.api.Assertions.assertThat;

class FreeImage4JavaTest {
    private static final int IMAGE_WIDTH = 256;
    private static final int IMAGE_HEIGHT = 256;

    private static byte[] pixels;

    static {
        FreeImage_Initialise(TRUE());
    }

    @BeforeAll
    public static void setup() {
        pixels = createPixels(IMAGE_WIDTH, IMAGE_HEIGHT);
    }

    @Test
    public void shouldLoadImage() {
        try (var arena = Arena.ofConfined()) {
            var dib = NULL;
            try {
                var inputFileName = arena.allocateFrom(getFileName("sample.png"));
                dib = FreeImage_Load(FIF_PNG(), inputFileName, PNG_DEFAULT());
                assertThat(dib).isNotNull();
                assertThat(dib.address()).isNotZero();
                assertThat(comparePixels(arena, dib, pixels)).isZero();
                var outputFileName = arena.allocateFrom(getFileName("test0.png"));
                int result = FreeImage_Save(FIF_PNG(), dib, outputFileName, PNG_DEFAULT());
                assertThat(result).isEqualTo(1);
            }
            finally {
                if (!dib.equals(NULL)) {
                    FreeImage_Unload(dib);
                }
            }
        }
    }

    @Test
    public void shouldSaveImage() {
        try (var arena = Arena.ofConfined()) {
            var dib = NULL;
            try {
                dib = FreeImage_Allocate(IMAGE_WIDTH, IMAGE_HEIGHT, 32, 0x00FF0000, 0x0000FF00, 0x000000FF);
                assertThat(dib).isNotNull();
                assertThat(dib.address()).isNotZero();
                setImagePixels(arena, dib, pixels);
                var outputFileName = arena.allocateFrom(getFileName("test1.png"));
                int result = FreeImage_Save(FIF_PNG(), dib, outputFileName, PNG_DEFAULT());
                assertThat(result).isEqualTo(1);
            } finally {
                if (!dib.equals(NULL)) {
                    FreeImage_Unload(dib);
                }
            }
        }
    }

    @Test
    public void shouldSaveAndLoadSameImage() {
        try (var arena = Arena.ofConfined()) {
            var dib1 = NULL;
            var dib2 = NULL;
            try {
                dib1 = FreeImage_Allocate(IMAGE_WIDTH, IMAGE_HEIGHT, 32, 0x00FF0000, 0x0000FF00, 0x000000FF);
                assertThat(dib1).isNotNull();
                assertThat(dib1.address()).isNotZero();
                setImagePixels(arena, dib1, pixels);
                var outputFileName = arena.allocateFrom(getFileName("test2.png"));
                FreeImage_Save(FIF_PNG(), dib1, outputFileName, PNG_DEFAULT());
                dib2 = FreeImage_Load(FIF_PNG(), outputFileName, PNG_DEFAULT());
                assertThat(dib2).isNotNull();
                assertThat(dib2.address()).isNotZero();
                assertThat(comparePixels(arena, dib2, dib1)).isZero();
            } finally {
                if (!dib1.equals(NULL)) {
                    FreeImage_Unload(dib1);
                }
                if (!dib2.equals(NULL)) {
                    FreeImage_Unload(dib2);
                }
            }
        }
    }

    private int comparePixels(Arena arena, MemorySegment dib1, MemorySegment dib2) {
        MemorySegment value1 = arena.allocate(tagRGBQUAD.layout());
        MemorySegment value2 = arena.allocate(tagRGBQUAD.layout());
        int result = 0;
        for (int y = 0; y < IMAGE_HEIGHT; y++) {
            for (int x = 0; x < IMAGE_WIDTH; x++) {
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

    private int comparePixels(Arena arena, MemorySegment dib, byte[] pixels) {
        MemorySegment value = arena.allocate(tagRGBQUAD.layout());
        int result = 0;
        for (int y = 0; y < IMAGE_HEIGHT; y++) {
            int j = y * IMAGE_WIDTH;
            for (int x = 0; x < IMAGE_WIDTH; x++) {
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

    private void setImagePixels(Arena arena, MemorySegment dib, byte[] pixels) {
        MemorySegment value = arena.allocate(tagRGBQUAD.layout());
        for (int y = 0; y < IMAGE_HEIGHT; y++) {
            int j = y * IMAGE_WIDTH;
            for (int x = 0; x < IMAGE_WIDTH; x++) {
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
        return STR."\{System.getProperty("basedir", ".")}/\{file}";
    }
}