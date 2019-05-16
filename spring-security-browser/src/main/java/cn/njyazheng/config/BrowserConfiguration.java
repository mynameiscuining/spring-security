package cn.njyazheng.config;

import cn.njyazheng.auth.CustomAuthFailHandler;
import cn.njyazheng.auth.CustomAuthSuccessHandler;
import cn.njyazheng.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BrowserConfiguration extends WebSecurityConfigurerAdapter {
    //    @Autowired
//    private CustomUserDetailsService customUserDetailsServic;
    @Value("${custom.security.browser.login-page:/login.html}")
    private String loginPage;
    @Autowired
    private CustomAuthSuccessHandler customAuthSuccessHandler;
    @Autowired
    private CustomAuthFailHandler customAuthFailHandler;
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailsServic).passwordEncoder(passwordEncoder());
//    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //弹出窗alert
        // http.httpBasic();
        //表单登录,本版本默认方式
        http.formLogin()
                //自定义页面
                //.loginPage("/login.html")
                //只要需要认证的页面,就要跳转的url
                .loginPage("/authentication/require")
                //登录页认证请求,过滤器UsernamePasswordAuthenticationFilter会进行拦截
                .loginProcessingUrl("/authentication/form")
                //设置认证成功后的行为,默认会跳转到原来请求的地址上
                .successHandler(customAuthSuccessHandler)
                //设置认证失败后的行为
                .failureHandler(customAuthFailHandler);
        //---------------------------------------------------------------------------------------------
        //关闭跨站请求
        http.csrf().disable();
        //---------------------------------------------------------------------------------------------
        //对任何请求进行认证
        //对请求授权,
        http.authorizeRequests()
                //匹配的url,不需要做认证
                // .antMatchers("/login.html").permitAll()
                .antMatchers("/authentication/require",
                        //设置登录页不认证
                        loginPage).permitAll()
                //任何请求
                .anyRequest()
                //认证
                .authenticated();
    }
}
