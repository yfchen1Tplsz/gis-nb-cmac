package com.projecty.component.minio.infrastructure;

import lombok.experimental.UtilityClass;

import java.util.UUID;

/**
 * @author 陈宇锋
 * @date 2024/1/5
 */
@UtilityClass
public class FileUtils {
    public static void checkFile() {
    }

    public static String generateObjectId(String name) {
        return UUID.randomUUID()+name;
    }

    public static String generateFileId(String objectId, String contentType) {
        String sourceFileId = objectId + MinioConstants.DOLLAR + contentType;
        return AesEncryptUtils.encrypt(sourceFileId);
    }

    public static String parseObjectId(String fileId) {
        String sourceFileId = AesEncryptUtils.decrypt(fileId);
        return sourceFileId.substring(0, sourceFileId.lastIndexOf(MinioConstants.DOLLAR));
    }
}
