package com.arcsoft;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class ASVLOFFSCREEN extends Structure {
    public int u32PixelArrayFormat;
    public int i32Width;
    public int i32Height;
    public final Pointer[] ppu8Plane = new Pointer[4];
    public final int[] pi32Pitch = new int[4];
    
    public ASVLOFFSCREEN(){
    
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("u32PixelArrayFormat", "i32Width", "i32Height", "ppu8Plane", "pi32Pitch");
    }
}
