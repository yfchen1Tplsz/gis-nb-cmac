package com.projecty.component.repeat;

import java.util.Optional;

/**
 * @author 陈宇锋
 * @date 2023/11/29
 */
public interface identityKeyProvider {
    Optional<String> extractIdentityKey();
}
