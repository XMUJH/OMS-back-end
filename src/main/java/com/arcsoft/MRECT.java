package com.arcsoft;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class MRECT extends Structure {
    public static class ByValue extends MRECT implements Structure.ByValue {
        public ByValue() {
            
        }
        
        public ByValue(Pointer p) { 
            super(p); 
        }
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("left", "top", "right", "bottom");
    }

    public int left;
    public int top;
    public int right;
    public int bottom;
    
    public MRECT() {

    }
    
    public MRECT(Pointer p) {
        super(p);
        read();
    }

    public static class ByReference extends MRECT implements Structure.ByReference {
        public ByReference() {

        }

        public ByReference(Pointer p) {
            super(p);
        }
    }
}