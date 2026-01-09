package com.jeeplus.security.filter;
import cn.hutool.core.util.StrUtil;
import com.jeeplus.security.util.ParamUtils;
import cn.hutool.http.HtmlUtil;
import com.alibaba.fastjson2.JSON;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }


    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (!StrUtil.hasEmpty(value)) {
            value = HtmlUtil.filter(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                if (!StrUtil.hasEmpty(value)) {
                    value = HtmlUtil.filter(value);
                }
                values[i] = value;
            }
        }
        return values;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> parameters = super.getParameterMap();
        Map<String, String[]> map = new LinkedHashMap<>();
        if (parameters != null) {
            for (String key : parameters.keySet()) {
                String[] values = parameters.get(key);
                for (int i = 0; i < values.length; i++) {
                    String value = values[i];
                    if (!StrUtil.hasEmpty(value)) {
                        value = HtmlUtil.filter(value);
                    }
                    values[i] = value;
                }
                map.put(key, values);
            }
        }
        return map;
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (!StrUtil.hasEmpty(value)) {
            value = HtmlUtil.filter(value);
        }
        return value;
    }




    @Override
    public ServletInputStream getInputStream() throws IOException {
        InputStream in = super.getInputStream();
        StringBuffer body = new StringBuffer();
        InputStreamReader reader = new InputStreamReader(in, Charset.forName("UTF-8"));
        BufferedReader buffer = new BufferedReader(reader);
        String line = buffer.readLine();
        while (line != null) {
            body.append(line);
            line = buffer.readLine();
        }
        buffer.close();
        reader.close();
        in.close();
        Map<String, Object> map = JSON.parseObject(body.toString());
        Map<String, Object> resultMap = new HashMap(map.size());
        String sql = "";//sql注入
        String filePath = "";//文件路径穿透处理
        for (String key : map.keySet()) {
            Object val = map.get(key);

            //sql注入处理 只过滤orders字段

            if (map.get(key) instanceof String) {
                if(StringUtils.isNotBlank(key)&&key.contains("orders")){
                    if (map.get(key) instanceof String) {
                        sql+=val.toString();
                    }
                }
                if(StringUtils.isNotBlank(key)&&key.contains("uploadPath")){
                    if (map.get(key) instanceof String) {
                        filePath+=val.toString();
                    }
                }
                String str = val.toString();
                //str =  ParamUtils.cleanXSS(str);
                resultMap.put(key, HtmlUtil.filter(str));
            } else {
                resultMap.put(key, val);
            }
        }
        if (ParamUtils.sqlValidate(sql)||ParamUtils.filePathValidate(filePath)) {
            //TODO 这里直接抛异常处理，前后端交互项目中，请把错误信息按前后端"数据返回的VO"对象进行封装
            throw new IOException("您发送请求中的参数中含有非法字符");

        }
        String str = JSON.toJSONString(resultMap);
        final ByteArrayInputStream bain = new ByteArrayInputStream(str.getBytes());
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return bain.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {
            }
        };
    }



}
