module gis.model{
    requires transitive gis.core;
    requires transitive lombok;
    requires  jakarta.persistence;
    requires  org.hibernate.orm.core;
    requires  org.hibernate.orm.envers;
    requires  spring.data.jpa;
    requires  spring.core;
    requires  jakarta.annotation;
    requires transitive spring.context;
    requires  spring.data.commons;
    requires transitive spring.boot.autoconfigure;
    requires  spring.security.core;
    requires  com.querydsl.core;
    requires  org.mapstruct;
    requires com.querydsl.jpa;
    requires java.compiler;
    exports com.projecty.model to gis.application;
    exports com.projecty.model.domain.policy.factory to gis.application;
    exports com.projecty.model.domain.policy.aggregate to gis.application;
    exports com.projecty.model.domain.policy.persist to gis.application;
    exports com.projecty.model.domain.policy.service to gis.application;
}