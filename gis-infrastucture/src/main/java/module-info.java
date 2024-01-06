module gis.infrastructure {

    requires lombok;
    //输入输出模型序列化依赖模块
    requires com.fasterxml.jackson.annotation;
    //Input模型校验依赖模块
    requires jakarta.validation;
    requires spring.core;
    requires spring.security.core;
    requires transitive org.zalando.problem;
    requires transitive com.google.gson;
    exports com.projecty.infrastucture.validator;
    exports com.projecty.infrastucture.constants;
    exports com.projecty.infrastucture.exception;
    exports com.projecty.infrastucture.enums;
    exports com.projecty.infrastucture.utils;
}