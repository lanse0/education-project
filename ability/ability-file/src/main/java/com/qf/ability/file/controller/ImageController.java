package com.qf.ability.file.controller;

import com.alibaba.fastjson.JSONObject;
import com.qf.commons.data.result.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * author 14526
 * create_time 2023/3/5
 */
@RestController
@RequestMapping("/file/img")
@Slf4j
public class ImageController {

    private String uploadPath = "D://imgs";

    /**
     * @param file
     * @param type 模块标识 识别从哪上传的图片 返回相应的回参
     * @return
     */
    @RequestMapping("/uploader")
    public Object uploader(MultipartFile file, Integer type) {

        File myFile = new File(uploadPath); //创建一个文件对象
        if (!myFile.exists()) myFile.mkdirs(); //若文件夹不存在 创建该文件夹

        //生成文件名称
        String oldName = file.getOriginalFilename();
        log.debug("[file uploader] 旧文件名：{}", oldName);
        String fileName = UUID.randomUUID().toString() + oldName.substring(oldName.indexOf("."));
        log.debug("[file uploader] 新文件名：{}", fileName);

        try (
                //上传的输入流
                InputStream in = file.getInputStream();
                //输出的输出流
                OutputStream out = new FileOutputStream(new File(myFile, fileName));
                //在try里面 输出流不用关闭
        ) {
            IOUtils.copy(in, out);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        log.debug("[file uploader] 文件上传成功 - {}", uploadPath + "/" + fileName);


        if (type != null && type == 1) {
            //editor模块上传的图片 配置特定的返回参数
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("errno", 0);

            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("url", "http://localhost/file/img/download?imgName=" + fileName);

            jsonObject.put("data", jsonObject2);
            return jsonObject.toJSONString();
        } else {
            //把文件名返回
            return R.create(fileName);
        }
    }

    /**
     * 图片下载/访问
     * 用输入流将文件读取，再复制给response输出流
     */
    @RequestMapping("/download")
    public void downLoad(String imgName, HttpServletResponse response) {
        log.debug("[img download] 获取图片 - {}", imgName);
        try (
                InputStream in = new FileInputStream(new File(uploadPath, imgName));
                ServletOutputStream out = response.getOutputStream();
        ) {
            IOUtils.copy(in, out);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
