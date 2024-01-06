package com.projecty.infrastucture.validator;

import com.projecty.infrastucture.exception.BusinessException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
public class EnumValueValidator implements ConstraintValidator<EnumValue, String> {
    private Class<? extends Enum<?>> enumClass;
    private String methodName;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        enumClass = constraintAnnotation.enumClass();
        methodName = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(value)) {
            return true; // 允许为空
        }
        try {
            Method method = enumClass.getDeclaredMethod(methodName);
            for (Enum<?> enumValue : enumClass.getEnumConstants()) {
                String code = (String) method.invoke(enumValue);
                if (code.equals(value)) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), "");
        }
        return false;
    }
}