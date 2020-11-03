package com.sm.cn.controller;

import com.sm.cn.http.AjaxResult;
import com.sm.cn.upload.FileUtils;
import com.sm.cn.upload.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @Auther: tjp
 * @Date: 2020/10/21 11:28
 * @Description:
 */
@RestController
public class UploadController {
@Autowired
private UploadService uploadService;
    @PostMapping("upload")
    public AjaxResult upload(@RequestPart("file") javax.servlet.http.Part avatar) throws IOException {
        String fileName = FileUtils.getFileName(avatar);


      return uploadService.uploadService(avatar.getInputStream(),fileName,avatar.getSize());


  }


}