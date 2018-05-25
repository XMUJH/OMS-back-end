package com.arcsoft.utils;

public class BufferInfo {
    public final int width;
    public final int height;
    public final byte[] buffer;

    public BufferInfo(int w, int h, byte[] buf) {
        width = w;
        height = h;
        buffer = buf;
    }
}