package com.projecty.component.minio.config;

import com.projecty.component.minio.structure.Bucket;
import com.projecty.component.minio.structure.RoleMinioClient;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.util.Assert;
/**
 * @author 陈宇锋
 * @date 2024/1/5
 */
@Configuration
@Slf4j
@DependsOn("adminMinioClient")
public class BucketConfig implements InitializingBean {
    @Resource(name = "adminMinioClient")
    private RoleMinioClient adminMinioClient;
    @Value(value = "${minio.default-bucket}")
    private String defaultBucketName;
    @Value(value = "${minio.public-bucket}")
    private String publicBucketName;


    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(defaultBucketName, "Minio defaultBucketName 为空");
        Assert.hasText(publicBucketName, "Minio publicBucketName 为空");
        log.info("try init default-bucket: {}",defaultBucketName);
        createBucketIfNotExists(defaultBucketName);
        log.info("try init public-bucket: {}",publicBucketName);
        createBucketIfNotExists(publicBucketName);
    }

    @Bean(name = "defaultBucket")
    public Bucket defaultBucket() {
        return new Bucket(defaultBucketName);
    }

    @Bean(name = "publicBucket")
    public Bucket publicBucket() {
        return new Bucket(publicBucketName);
    }

    private void createBucketIfNotExists(String bucketName) throws Exception {
        boolean found =
                adminMinioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!found) {
            log.info("bucket {} not exists, create this bucket...", bucketName);
            adminMinioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } else {
            log.info("bucket {} exists", bucketName);
        }
    }
}
