package cn.njyazheng.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class BrowserConfiguration  extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       //弹出窗alert
       // http.httpBasic();
        //表单登录,本版本默认方式
        http.formLogin();
        //对任何请求进行认证
        //对请求授权,
        http.authorizeRequests()
                //任何请求
                .anyRequest()
                //认证
                .authenticated();
    }
}
