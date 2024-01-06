package com.projecty.infrastucture.exception;

import com.projecty.infrastucture.constants.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
public class BusinessException extends AbstractThrowableProblem {
    private static final long serialVersionUID = 1L;
    private final Object resource;
    private final String errorKey;

    public BusinessException(String messageTitle, String errorKey) {
        this(messageTitle, null, null, errorKey);
    }

    public BusinessException(String messageTitle, String messageDetail, String errorKey) {
        this(ErrorConstants.APPLICATION_EXCEPTION_TYPE, messageTitle, messageDetail, null, errorKey);
    }
    public BusinessException(String messageTitle, String messageDetail, Object resource, String errorKey) {
        this(ErrorConstants.APPLICATION_EXCEPTION_TYPE, messageTitle, messageDetail, resource, errorKey);
    }

    public BusinessException(URI type, String messageTitle, String messageDetail, Object resource, String errorKey) {
        super(type, messageTitle, Status.BAD_REQUEST, messageDetail, null, null, getAdditionalParameters(resource, errorKey));
        this.resource = resource;
        this.errorKey = errorKey;
    }

    public Object getResource() {
        return resource;
    }

    public String getErrorKey() {
        return errorKey;
    }

    private static Map<String, Object> getAdditionalParameters(Object resource, String errorKey) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", "error." + errorKey);
        parameters.put("params", resource);
        return parameters;
    }
}

