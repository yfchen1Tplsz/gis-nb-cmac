package com.projecty.component.minio.structure;

import io.minio.MinioClient;
import lombok.Getter;
/**
 * @author 陈宇锋
 * @date 2024/1/5
 */
@Getter
public class RoleMinioClient extends MinioClient {
    private String role;

    public RoleMinioClient(String role,MinioClient minioClient){
        super(minioClient);
        this.role = role;
    }
}
