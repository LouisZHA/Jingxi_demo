package com.capgemini.jingxi_demo.representation.controller.common;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@RequiredArgsConstructor
public class CommonController {

    private String filePath = "/Users/Mac/IdeaProjects/Jingxi_demo/src/main/resources/photos/";

    // 上传图片
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String upload(MultipartFile file)throws IllegalStateException {
        File filee=new File(filePath);
        if(!filee.exists()){
            filee.mkdirs();
        }

        try {
            // 文件名称
            String originalFileName = file.getOriginalFilename();
            // 截取原始文件名的后缀
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            // 构造新文件名称
            String newFileName = UUID.randomUUID() + extension;
            // 请求路径合成
            String newFilePath=filePath+newFileName;
            //将传来的文件写入新建的文件
            file.transferTo(new File(newFilePath));

            return newFilePath;
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
