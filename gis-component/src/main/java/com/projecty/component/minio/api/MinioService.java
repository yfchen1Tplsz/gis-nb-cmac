package com.projecty.component.minio.api;

import com.projecty.application.adapt.FileStoreInterface;
import com.projecty.component.minio.context.MinioContext;
import com.projecty.component.minio.context.MinioContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
/**
 * @author 陈宇锋
 * @date 2024/1/5
 */
@Service
public class MinioService  implements FileStoreInterface {
    @Override
    public String uploadFilePrivate(MultipartFile file){
        MinioContext minioContext = MinioContextHolder.getMinioContext();
        if(minioContext != null){
            return minioContext.uploadFilePrivate(file);
        }
        throw new RuntimeException("你没有权限操作文件");
    }

    @Override
    public InputStream loadFilePrivate(String fileId){
        MinioContext minioContext = MinioContextHolder.getMinioContext();
        if (minioContext != null){
            return minioContext.loadFilePrivate(fileId);
        }
        throw new RuntimeException("你没有权限操作该文件");
    }
}
