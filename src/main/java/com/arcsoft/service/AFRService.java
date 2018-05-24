package com.arcsoft.service;

import com.arcsoft.AFR_FSDK_FACEMODEL;
import com.arcsoft.ASVLOFFSCREEN;
import com.arcsoft.FaceInfo;
import com.sun.jna.Pointer;
import org.springframework.stereotype.Service;

@Service
public interface AFRService {
    String APPID = "BAmSD6xWv9rF1PsyXzSRY8EZFxzet7HfpnhGM9u9EDsn";
    String FD_SDKKEY = "43GSj94uH9EzohqJRCx7iZmjg9K1Lm463cp89GwEEqPk";
    String FR_SDKKEY = "43GSj94uH9EzohqJRCx7iZmrqYa9yr6z9sg8rt7KmQv4";

    int FD_WORKBUF_SIZE = 20 * 1024 * 1024;
    int FR_WORKBUF_SIZE = 40 * 1024 * 1024;
    int MAX_FACE_NUM = 50;

    boolean bUseRAWFile = false;
    boolean bUseBGRToEngine = true;

    boolean getThirdPartyFace();

    void setThirdPartyFace(boolean tag);

    String doFR(String inputImg, String[] faceDataPath);

    FaceInfo[] doFaceDetection(Pointer hFDEngine, ASVLOFFSCREEN inputImg);

    AFR_FSDK_FACEMODEL extractFRFeature(Pointer hFREngine, ASVLOFFSCREEN inputImg, FaceInfo faceInfo);

    float compareFaceSimilarity(Pointer hFDEngine, Pointer hFREngine, ASVLOFFSCREEN inputImgA, ASVLOFFSCREEN inputImgB);

    ASVLOFFSCREEN loadRAWImage(String yuv_filePath, int yuv_width, int yuv_height, int yuv_format);

    ASVLOFFSCREEN loadImage(String filePath);

}
