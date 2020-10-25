package com.sm.cn.upload;

import org.springframework.util.StringUtils;

import javax.servlet.http.Part;
import java.util.UUID;

public class FileUtils {


    public static String getFileName(Part part) {
        //获取文件后缀名
        String ext = StringUtils.getFilenameExtension(part.getSubmittedFileName());
        //随即生成文件名
        String fileName = UUID.randomUUID().toString().replaceAll("-", "");
//      通过fileName生成hashcode值
        int hashCode = fileName.hashCode();
//截取hash值路径一取后两位
        String dir1 = Integer.toHexString(hashCode & 0xff);
        String dir2 = Integer.toHexString((hashCode >> 8) & 0xff);
        String realPath = dir1 + "/" + dir2 + "/" + fileName + "." + ext;
        //目录打散后的路径
        return realPath;
    }
}
