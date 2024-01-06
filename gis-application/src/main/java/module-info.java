module gis.application{
    requires gis.model;
    requires jakarta.annotation;
    requires jakarta.transaction;
    requires jakarta.cdi;
    requires spring.web;
    exports com.projecty.application.service;
    exports com.projecty.application;
}