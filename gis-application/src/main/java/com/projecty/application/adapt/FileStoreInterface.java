package com.projecty.application.adapt;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author 陈宇锋
 * @date 2024/1/5
 */
public interface FileStoreInterface {

    String uploadFilePrivate(MultipartFile file);

    InputStream loadFilePrivate(String fileId);
}
