package com.projecty.component.minio.structure;
/**
 * @author 陈宇锋
 * @date 2024/1/5
 */
public class Bucket {
    protected String bucketName;

    public Bucket(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return this.bucketName;
    }
}