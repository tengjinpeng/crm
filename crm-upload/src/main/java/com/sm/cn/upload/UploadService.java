package com.sm.cn.upload;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.sm.cn.http.AjaxResult;
import com.sm.cn.http.AjaxStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Auther: tjp
 * @Date: 2020/10/21 18:40
 * @Description:
 */
@Component
@PropertySource(value = {"classpath:upload.properties"})
public class UploadService {
@Value("${endpoint}")
private String endpoint;
@Value("${accessKeyId}")
 private   String  accessKeyId;
@Value("${accessKeySecret}")
 private  String  accessKeySecret;
@Value("${bucketName}")
private   String   bucketName;
@Value("${baseUrl}")
private  String  baseUrl;
@Value("${ext}")
private String ext;
@Value("${imgSize}")
private int imgSize;//默认是兆 M
    public AjaxResult uploadService(InputStream in , String fileName,long size) throws IOException {

//        判断是否是图片
        byte[] buffer=new byte[in.available()];
        in.read(buffer);
        ByteArrayInputStream inputStream=new ByteArrayInputStream(buffer);
        BufferedImage bufferedImage=ImageIO.read(inputStream);

//        BufferedImage bufferedImage=ImageIO.read(in);会造成图片损坏
        if(Objects.isNull(bufferedImage)){
            return AjaxResult.error(AjaxStatus.NOT_IMAGE);
        }
//        是否包含指定格式
        String[] split = ext.split(",");
        if(!Arrays.asList(split).contains(StringUtils.getFilenameExtension(fileName))){
           return AjaxResult.error(AjaxStatus.EXT_ERROE);
       }

//大小是否符合
     if(size/1024/1024>imgSize){
         return  AjaxResult.error(AjaxStatus.FILE_TOLONG);
     }
//         目录打散 重命名
       ByteArrayInputStream inputStream1=new ByteArrayInputStream(buffer);
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, inputStream1);
        ossClient.shutdown();
      return AjaxResult.success(baseUrl+fileName) ;
    }
}