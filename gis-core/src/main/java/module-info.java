module gis.core {
    exports com.projecty.core.input.policy;
    exports com.projecty.core.output.policy;
    exports com.projecty.core.application to gis.application;
    requires transitive gis.infrastructure;
    requires lombok;
    //输入输出模型序列化依赖模块
    requires com.fasterxml.jackson.annotation;
    //swagger 依赖模块
    requires io.swagger.v3.oas.annotations;
    //Input模型校验依赖模块
    requires jakarta.validation;
    //QueryProjection Output生成Q类辅助查询
    requires com.querydsl.jpa;
    requires com.querydsl.core;
    requires com.querydsl.apt;
    //使用其中的工具类 StringUtils
    requires java.compiler;
    requires spring.core;
}