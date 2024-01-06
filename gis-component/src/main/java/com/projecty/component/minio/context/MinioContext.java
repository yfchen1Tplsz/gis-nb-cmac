package com.projecty.component.minio.context;


import com.projecty.component.minio.infrastructure.FileUtils;
import com.projecty.component.minio.structure.Bucket;
import com.projecty.component.minio.structure.RoleMinioClient;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
/**
 * @author 陈宇锋
 * @date 2024/1/5
 */
@Slf4j
public class MinioContext {
    private final Bucket defaultBucket;
    private final Bucket publicBucket;
    private final RoleMinioClient roleMinioClient;

    public MinioContext(Bucket defaultBucket, Bucket publicBucket, RoleMinioClient roleMinioClient) {
        Assert.notNull(defaultBucket,"defaultBucket 为空");
        Assert.notNull(publicBucket,"publicBucket 为空");
        Assert.notNull(roleMinioClient,"roleMinioClient 为空");
        this.defaultBucket = defaultBucket;
        this.publicBucket = publicBucket;
        this.roleMinioClient = roleMinioClient;
    }

    public String uploadFilePrivate(MultipartFile file){
        FileUtils.checkFile();
        String objectId = FileUtils.generateObjectId(file.getOriginalFilename());
        try {
            InputStream inputStream = file.getInputStream();
            roleMinioClient.putObject(
                    PutObjectArgs.builder()
                        .bucket(defaultBucket.getBucketName())
                        .object(objectId)
                        .stream(inputStream, inputStream.available(), -1)
                        .contentType(file.getContentType())
                        .build()
            );
            return FileUtils.generateFileId(objectId,file.getContentType());
        }catch (Exception e){
            log.info(" uploadFile error, msg: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public InputStream loadFilePrivate(String fileId){
        try{
            String objectId = FileUtils.parseObjectId(fileId);
            GetObjectResponse object = roleMinioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(defaultBucket.getBucketName())
                            .object(objectId)
                            .build()
            );
            return object;
        }catch (Exception e){
            log.info(" loadFile error, msg: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
