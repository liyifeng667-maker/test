package com.jeeplus.security.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 修改黑盒 referer未校验&跨域 问题
 */
@Component
@Configuration
@Order(1)
@WebFilter(filterName = "refererFilter", urlPatterns = "/*")
public class RefererFilterConfig implements Filter {
	public static final Logger logger = LoggerFactory.getLogger(RefererFilterConfig.class);
	/**
	 * 过滤器配置对象
	 */
	FilterConfig filterConfig = null;
 
	/**
	 * 是否启用
	 */
	private boolean enable = false;
 
 
	/**
	 * 忽略的URL
	 */
	@Value("${security.csrf.excludes}")
	private String excludes;
 
 
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
 
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		logger.info("是否启用Referer 跨站点拦截器" + enable);
		HttpServletRequest request = (HttpServletRequest) servletRequest;
 
		// 不启用或者已忽略的URL不拦截
		if (!enable || isExcludeUrl(request.getServletPath())) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
 
		String referer = request.getHeader("Referer");
		String origin = request.getHeader("Origin");
		String serverName = request.getServerName();
 
		// 判断是否存在外链请求本站
		if ((null != referer && referer.indexOf(serverName) < 0) || (null != origin && origin.indexOf(serverName) < 0)) {
			logger.error("Referer过滤器 => 服务器：{} => 当前域名：{}", serverName, referer);
			servletResponse.setContentType("text/html; charset=utf-8");
			servletResponse.getWriter().write("系统不支持当前域名的访问！");
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}
 
	@Override
	public void destroy() {
		this.filterConfig = null;
	}
 
	/**
	 * 判断是否为忽略的URL
	 * 
	 * @param urlPath
	 *            URL路径
	 * @return true-忽略，false-过滤
	 */
	private boolean isExcludeUrl(String urlPath) {
		if (excludes == null || excludes.isEmpty()) {
			return false;
		}
		List<String> urls = Arrays.asList(excludes.split(","));
		return urls.stream().map(pattern -> Pattern.compile("^" + pattern)).map(p -> p.matcher(urlPath))
				.anyMatch(Matcher::find);
	}
 
}