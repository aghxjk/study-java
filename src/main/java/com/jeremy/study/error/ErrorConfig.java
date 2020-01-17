package com.jeremy.study.error;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {

        /**
         * Spring Boot 为我们提供了统一异常处理方式。通过实现ErrorPageRegistrar 接口来注册异常错误提示页面
         * 注册：404 请求异常
         * 把默认的错误页面设置为 /index.html
         * 否则: http://localhost:8443/login 会请求失败
         */
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
        registry.addErrorPages(error404Page);
    }
}
