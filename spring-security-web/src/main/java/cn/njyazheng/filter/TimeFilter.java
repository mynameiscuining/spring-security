package cn.njyazheng.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
//加载拦截器方式一,注入容器
//@Component
public class TimeFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("-----------------------TimeFilter init");
    }
    //此处理过滤器逻辑
    //filer局限:是不知道请求是对应哪些控制器的
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LocalTime start=LocalTime.now();
        LOGGER.info("-----------------------TimeFilter start");
        filterChain.doFilter(servletRequest,servletResponse);
        LOGGER.info("-----------------------TimeFilter end");
        LocalTime end=LocalTime.now();
        Duration duration=Duration.between(start,end);
        LOGGER.info("-----------------------TimeFilter execute time:"+duration.toMillis()+"ms");
    }
    
    @Override
    public void destroy() {
        LOGGER.info("-----------------------TimeFilter destroy");
    }
}
