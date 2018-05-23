package com.wedo.OMS.controller;

import com.arcsoft.service.AFRService;
import com.wedo.OMS.utils.FileUtil;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@ComponentScan(value="com.arcsoft")
public class FaceController {
    private AFRService afrService;

    public FaceController(AFRService afrService) {
        this.afrService=afrService;
    }

    @PostMapping(value = "/facerecognition")
    public @ResponseBody int faceRecogintion(@RequestParam("img") MultipartFile file, HttpServletRequest request) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        String filePath = "src/main/resources/static/faceTemp";
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        String result = afrService.doFR("src/main/resources/static/faceTemp/"+fileName, new String[]{"src/main/resources/static/faceimg/001.jpg", "src/main/resources/static/faceimg/002.jpg", "src/main/resources/static/faceimg/003.jpg"}).toString();
        if(result=="Warning! Third Party Faces Detected") return 3;
        if(result=="Recognition Successful!") return 1;
        if(result=="Recognition Failed!") return 2;
        else return -1;
    }
}
