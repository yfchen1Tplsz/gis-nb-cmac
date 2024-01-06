package com.projecty.component.minio.context;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.projecty.component.minio.structure.Bucket;
import com.projecty.component.minio.structure.RoleMinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @author 陈宇锋
 * @date 2024/1/5
 */
@Service
@Slf4j
@DependsOn("bucketConfig")
public class MinioContextFactory {
    private final List<RoleMinioClient> roleMinioClientList;

    private final Bucket defaultBucket;
    private final Bucket publicBucket;
    private final static  Map<String, MinioContext> MINIO_CONTEXT_MAP = new ConcurrentHashMap();

    public MinioContextFactory(List<RoleMinioClient> roleMinioClientList, @Qualifier(value = "defaultBucket") Bucket defaultBucket,@Qualifier(value = "publicBucket")  Bucket publicBucket) {
        this.roleMinioClientList = roleMinioClientList;
        this.defaultBucket = defaultBucket;
        this.publicBucket = publicBucket;
        this.initMinioContextMap();
    }

    private void initMinioContextMap() {
        if(CollectionUtil.isEmpty(roleMinioClientList)){
            return;
        }
        roleMinioClientList.forEach( roleMinioClient -> {
                if(MINIO_CONTEXT_MAP.get(roleMinioClient.getRole()) != null){
                    log.info(StrUtil.format("存在相同 ROLE minioClient实例  : role = {}", roleMinioClient.getRole()));
                    throw new IllegalStateException("init role minio client failed,plz check!");
                }
                MINIO_CONTEXT_MAP.put(roleMinioClient.getRole(),new MinioContext(defaultBucket,publicBucket,roleMinioClient) );
            }
        );
    }

    public static MinioContext getMinioContext(String role){
        return MINIO_CONTEXT_MAP.get(role);
    }

}
