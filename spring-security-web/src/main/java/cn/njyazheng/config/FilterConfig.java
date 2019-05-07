package cn.njyazheng.config;

import cn.njyazheng.filter.TimeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;

@Configuration
public class FilterConfig {
    @Bean
    //加载拦截器方式二
    //加入第三方过滤器
    public FilterRegistrationBean timefilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        TimeFilter timeFilter=new TimeFilter();
        filterRegistrationBean.setFilter(timeFilter);
        //设置拦截的url
        List<String>urls=new LinkedList<>();
        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);
        return filterRegistrationBean;
    }
}
