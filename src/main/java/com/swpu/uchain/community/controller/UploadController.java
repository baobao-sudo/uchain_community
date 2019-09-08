package com.swpu.uchain.community.controller;

import com.swpu.uchain.community.enums.ResultEnum;
import com.swpu.uchain.community.util.ResultVOUtil;
import com.swpu.uchain.community.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 有关下载的
 * @author baobao
 * @date 2019-8-31
 */
@RestController
@RequestMapping("/admin")
public class UploadController {

    @PostMapping("/upload")
    public ResultVO upload(@RequestParam("file")CommonsMultipartFile commonsMultipartFile){
        if(!commonsMultipartFile.isEmpty()){
            try {
                FileOutputStream fileOutputStream = new FileOutputStream("/home/baobao/picture" + commonsMultipartFile.getOriginalFilename());
                InputStream inputStream = commonsMultipartFile.getInputStream();
                int b = 0;
                while((b=inputStream.read()) != -1){
                    fileOutputStream.write(b);
                }
                fileOutputStream.flush();
                inputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResultVOUtil.success();
    }

    @PostMapping("/test")
    public ResultVO load(@RequestParam("file")MultipartFile file) {

        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        String newFileName = System.currentTimeMillis() + suffix;
        String path = "/home/baobao/picture";
        File newFile = new File(path + newFileName);
        if (!file.isEmpty()) {
            try {
                file.transferTo(newFile);
                return ResultVOUtil.success();
            } catch (IOException e) {
                e.printStackTrace();
                return ResultVOUtil.error(ResultEnum.FILE_UPLOAD_FAILE);
            }

        }
        return ResultVOUtil.error(ResultEnum.FILE_IS_EMPTY);
    }


}
