package com.xuecheng.media;
import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import io.minio.*;
import io.minio.errors.MinioException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @description 测试MinIO
 * @author Mr.M
 * @date 2022/9/11 21:24
 * @version 1.0
 */
public class MinioTest {

    static MinioClient minioClient =
            MinioClient.builder()
                    .endpoint("http://192.168.101.65:9000")
                    .credentials("minioadmin", "minioadmin")
                    .build();
    //上传文件
    @Test
    public void upload() {
        //通过扩展名取出mimeType
        ContentInfo extensionMatch = ContentInfoUtil.findExtensionMatch(".mp4");
        String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;//通用mimeType，字节流
        if(extensionMatch!=null){
            mimeType = extensionMatch.getMimeType();
        }

        try {
            UploadObjectArgs testbucket = UploadObjectArgs.builder()
                    .bucket("testbucket")
//                    .object("test001.mp4")
                    .filename("D:\\preview.mp3")//本地文件路径+名
                    .object("mp3/test002.mp3")//在bucket中添加子目录
//                    .contentType("video/mp4")//默认根据扩展名确定文件内容类型，也可以指定
                    .contentType(mimeType)
                    .build();
            minioClient.uploadObject(testbucket);
            System.out.println("上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("上传失败");
        }
    }

    @Test
    public void delete(){
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket("testbucket").object("test001.mp3").build());
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除失败");
        }
    }

    //查询文件
    @Test
    public void getFile() {
        GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket("testbucket").object("mp3/test001.mp3").build();
        try{

            //从网络中取得远程流
            FilterInputStream inputStream = minioClient.getObject(getObjectArgs);
            //输出流
            FileOutputStream outputStream = new FileOutputStream(new File("D:\\1.mp3"));
            IOUtils.copy(inputStream,outputStream);

            //校验文件的完整性对文件的内容进行md5
            FileInputStream fileInputStream1 = new FileInputStream(new File("D:\\preview.mp3"));
            String source_md5 = DigestUtils.md5Hex(fileInputStream1);

            FileInputStream fileInputStream = new FileInputStream(new File("D:\\1.mp3"));
            String local_md5 = DigestUtils.md5Hex(fileInputStream);
            if(source_md5.equals(local_md5)){
                System.out.println("下载成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}