package com.arcsoft.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.arcsoft.AFD_FSDKLibrary;
import com.arcsoft.AFD_FSDK_FACERES;
import com.arcsoft.AFD_FSDK_Version;
import com.arcsoft.AFR_FSDKLibrary;
import com.arcsoft.AFR_FSDK_FACEINPUT;
import com.arcsoft.AFR_FSDK_FACEMODEL;
import com.arcsoft.AFR_FSDK_Version;
import com.arcsoft.ASVLOFFSCREEN;
import com.arcsoft.ASVL_COLOR_FORMAT;
import com.arcsoft.CLibrary;
import com.arcsoft.FaceInfo;
import com.arcsoft.MRECT;
import com.arcsoft._AFD_FSDK_OrientPriority;
import com.arcsoft.utils.BufferInfo;
import com.arcsoft.utils.ImageLoader;
import com.sun.jna.Memory;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.PointerByReference;
import org.springframework.stereotype.Service;

@Service
public interface AFRService {
    static final String    APPID  = "BAmSD6xWv9rF1PsyXzSRY8EZFxzet7HfpnhGM9u9EDsn";
    static final String FD_SDKKEY = "43GSj94uH9EzohqJRCx7iZmjg9K1Lm463cp89GwEEqPk";
    static final String FR_SDKKEY = "43GSj94uH9EzohqJRCx7iZmrqYa9yr6z9sg8rt7KmQv4";

    static final int FD_WORKBUF_SIZE = 20 * 1024 * 1024;
    static final int FR_WORKBUF_SIZE = 40 * 1024 * 1024;
    static final int MAX_FACE_NUM = 50;

    static final boolean bUseRAWFile = false;
    static final boolean bUseBGRToEngine = true;

    boolean getThirdPartyFace();

    void setThirdPartyFace(boolean tag);

    String doFR(String inputImg, String[] faceDataPath);

    FaceInfo[] doFaceDetection(Pointer hFDEngine, ASVLOFFSCREEN inputImg);

    AFR_FSDK_FACEMODEL extractFRFeature(Pointer hFREngine, ASVLOFFSCREEN inputImg, FaceInfo faceInfo);

    float compareFaceSimilarity(Pointer hFDEngine, Pointer hFREngine, ASVLOFFSCREEN inputImgA, ASVLOFFSCREEN inputImgB);

    ASVLOFFSCREEN loadRAWImage(String yuv_filePath, int yuv_width, int yuv_height, int yuv_format);

    ASVLOFFSCREEN loadImage(String filePath);

}
