package com.wedo.OMS.controller;

import com.wedo.OMS.entity.Resource;
import com.wedo.OMS.service.ResourceService;
import com.wedo.OMS.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
    private final Logger logger = LoggerFactory.getLogger(UploadController.class);
    private ResourceService resourceService;

    public UploadController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    //TODO Should not upload files to project folder
    @RequestMapping(value = "/upload/resources", method = RequestMethod.POST)
    public @ResponseBody
    long uploadResource(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String filePath = "src/main/resources/static/resource/";
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return -1;
        }
        //TODO Consider to return status code and message
        Resource resource = resourceService.addResource(fileName);
        return resource.getId();
    }

    @RequestMapping(value = "/upload/results", method = RequestMethod.POST)
    public @ResponseBody
    String uploadResult(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String filePath = "src/main/resources/static/result/";
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return "upload fail";
        }
        //TODO Consider to return status code and message
        return "upload success";
    }

    @RequestMapping(value = "/upload/taskFiles", method = RequestMethod.POST)
    public @ResponseBody
    String uploadTaskFile(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String filePath = "src/main/resources/static/taskfile/";
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return "upload fail";
        }
        //TODO Consider to return status code and message
        return "upload success";
    }
}

