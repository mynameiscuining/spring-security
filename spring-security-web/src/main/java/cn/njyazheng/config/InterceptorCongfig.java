package cn.njyazheng.config;

import cn.njyazheng.interceptor.AsyncIntercepter;
import cn.njyazheng.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorCongfig implements WebMvcConfigurer {
    @Autowired
    private TimeInterceptor timeInterceptor;
    @Autowired
    private AsyncIntercepter asyncIntercepter;
    
    @Override
    //rest请求异步情况下,注册拦截器
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        
        configurer.registerCallableInterceptors(asyncIntercepter)
                //设置超时时间
                .setDefaultTimeout(5000);
    }
    
    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor).addPathPatterns("/**");
    }
}
