package com.jeeplus.security.filter;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * 过滤器
 * Created by adonis on 2020/12/12
 */
public class XssFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
                (HttpServletRequest) request);
        try {
            chain.doFilter(xssRequest, response);
        } catch (AccessDeniedException e) {//多端登录抛出异常
            throw new AccessDeniedException(e.getMessage());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("系统异常,请联系管理员处理");
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("系统异常,请联系管理员处理");
        }

    }

}





