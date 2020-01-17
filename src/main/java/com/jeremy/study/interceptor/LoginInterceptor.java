package com.jeremy.study.interceptor;

import com.jeremy.study.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 该拦截器(LoginInterceptor)配置在WebConfigurer中
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession httpSession = request.getSession();
        String preUri = request.getRequestURI();
        String contextPath = StringUtils.substring(preUri, 0, StringUtils.lastIndexOf(preUri,"/"));
        String[] requireAuthPages = new String[]{
                "index",
        };

        String uri = request.getRequestURI();
        String page = StringUtils.remove(uri, contextPath + "/");

        if (beginWith(page, requireAuthPages)) {
            User user = (User) httpSession.getAttribute("user");
            if (user == null) {
                response.sendRedirect("login");
                return false;
            }
        }
        return true;
    }

    private boolean beginWith(String page, String[] requireAuthPages) {
        boolean result = false;

        for (String requireAuthPage: requireAuthPages) {
            if (StringUtils.startsWith(page, requireAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }

}
