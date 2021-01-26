package com.funtil.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 文件上传控制器
 */
@Controller
public class UploadController {

    private static final String UPLOAD_PATH="/static/upload/";

    /**
     *文件上传
     * @param dropFile
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public Map<String,Object> upload(MultipartFile dropFile,MultipartFile[] editorFiles, HttpServletRequest request){
        Map<String,Object> result=new HashMap<>();

        //Dropzone上传
        if (dropFile!=null){
            result.put("fileName",writeFile(dropFile,request));
        }

        //wangEditor上传
        if (editorFiles!=null && editorFiles.length>0){
            List<String> fileNames=new ArrayList<>();

            for (MultipartFile editorFile:editorFiles){
                fileNames.add(writeFile(editorFile,request));
            }

            result.put("errno",0);
            result.put("data",fileNames);
        }
        return result;

        /*//前端上传的文件
        MultipartFile myFile = dropFile==null?editorFile:dropFile;

        //获取文件后缀
        String fileName = myFile.getOriginalFilename();
        String fileSuffix=fileName.substring(fileName.lastIndexOf("."));
        //System.out.println(request.getSession().getServletContext().getRealPath("/"));//"/"是绝对路径

        //文件存放路径
        String filePath=request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        //判断路径是否存在，如果不存在，则创建
        File file=new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }
        //将文件写入目标路径,UUID.randomUUID()+fileSuffix(随机名称)防止同名文件不能传入
        file=new File(filePath, UUID.randomUUID()+fileSuffix);
        try {
            myFile.transferTo(file);//实现写入
        } catch (IOException e) {
            e.printStackTrace();
        }

        //dropzone上传
        if(dropFile!=null){
            result.put("fileName",UPLOAD_PATH+file.getName());
        }
        //wangEditor上传
        else {
            //Scheme()服务端提供协议的开头http或https,ServerName域名，服务器名称，IP，ServerPort服务器的端口（8080）
            String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();


            result.put("errno", 0);
            result.put("data", new String[]{serverPath + UPLOAD_PATH + file.getName()});
        }

        return result;*/
    }

    private String writeFile(MultipartFile multipartFile, HttpServletRequest request) {
        //获取文件的后缀
        String fileName = multipartFile.getOriginalFilename();
        String fileSuffix=fileName.substring(fileName.lastIndexOf("."));

        //文件存放路径
        String filePath=request.getSession().getServletContext().getRealPath(UPLOAD_PATH);

        //判断路径是否存在，如果不存在，则创建
        File file=new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }

        //将文件写入目标路径,UUID.randomUUID()+fileSuffix(随机名称)防止同名文件不能传入
        file=new File(filePath, UUID.randomUUID()+fileSuffix);
        try {
            multipartFile.transferTo(file);//实现写入
        } catch (IOException e) {
            e.printStackTrace();
        }

        //返回文件的完整路径
        String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        return serverPath+UPLOAD_PATH+file.getName();

    }

}
