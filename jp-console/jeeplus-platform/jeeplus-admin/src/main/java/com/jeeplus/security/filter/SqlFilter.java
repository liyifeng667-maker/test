package com.jeeplus.security.filter;

import com.jeeplus.security.util.ParamUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @Auther: 睡竹
 * @Date: 2023/03/07
 * @Description: sql防注入过滤器
 */
@WebFilter(urlPatterns = "/*",filterName = "SqlFilter")
@Configuration
public class SqlFilter implements Filter {
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    /**
     * @description sql注入过滤
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest request = servletRequest;
        ServletResponse response = servletResponse;
       try{

           // 获得所有请求参数名
           Enumeration<String> names = request.getParameterNames();
           String sql = "";
           String filePath = "";//文件路径穿透处理
           while (names.hasMoreElements()){
               // 得到参数名
               String name = names.nextElement().toString();
               if(StringUtils.isNotBlank(name)&&name.contains("orders")){
                   // 得到参数对应值
                   String[] values = request.getParameterValues(name);
                   for (int i = 0; i < values.length; i++) {
                       sql += values[i];
                   }
               }

               if(StringUtils.isNotBlank(name)&&name.contains("uploadPath")){
                   // 得到参数对应值
                   String[] values = request.getParameterValues(name);
                   for (int i = 0; i < values.length; i++) {
                       filePath += values[i];
                   }
               }

           }

           if (ParamUtils.sqlValidate(sql)||ParamUtils.filePathValidate(filePath)) {
               //TODO 这里直接抛异常处理，前后端交互项目中，请把错误信息按前后端"数据返回的VO"对象进行封装
                throw new IOException("您发送请求中的参数中含有非法字符");

           } else {
               filterChain.doFilter(request,response);
           }
       }catch (Exception e){
           resolver.resolveException((HttpServletRequest)request, (HttpServletResponse)response, null, new IOException("您发送请求中的参数中含有非法字符"));
       }
    }



    public static void main(String[] args) {
      //  System.out.println(sqlValidate("select1"));

    }

    @Override
    public void destroy() {}
}
