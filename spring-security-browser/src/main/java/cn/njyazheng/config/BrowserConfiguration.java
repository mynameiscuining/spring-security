package cn.njyazheng.config;

import cn.njyazheng.core.auth.CustomAuthFailHandler2;
import cn.njyazheng.core.auth.CustomAuthSuccessHandler2;
import cn.njyazheng.core.ConfigProperties;
import cn.njyazheng.core.auth.config.AuthorizedConfigManager;
import cn.njyazheng.core.code.sms.auth.SmsCodeAuthenticatiionConfiguration;
import cn.njyazheng.core.code.sms.auth.SmsCodeFilter;
import cn.njyazheng.core.code.verify.auth.VerificationCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


@Configuration
public class BrowserConfiguration extends WebSecurityConfigurerAdapter {
    //    @Autowired
//    private CustomUserDetailsService customUserDetailsServic;
    @Autowired
    private ConfigProperties configProperties;
    
    //    @Autowired
//    private CustomAuthSuccessHandler customAuthSuccessHandler;
//    @Autowired
//    private CustomAuthFailHandler customAuthFailHandler;
    @Autowired
    private CustomAuthFailHandler2 customAuthFailHandler;
    @Autowired
    private CustomAuthSuccessHandler2 customAuthSuccessHandler;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SmsCodeAuthenticatiionConfiguration smsCodeAuthenticatiionConfiguration;
    @Autowired
    private AuthorizedConfigManager authorizedConfigManager;
    
    @Bean
    /**
     * 记住我Cookie的token去和数据库token比对,比对正确获取username,再去等录,获取Userdetails
     */
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //启动的时候会创建表,只能第一次使用
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }
    
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
        //添加验证码拦截器
        VerificationCodeFilter verificationCodeFilter = new VerificationCodeFilter();
        verificationCodeFilter.setCustomAuthFailHandler(customAuthFailHandler);
        verificationCodeFilter.setConfigProperties(configProperties);
        
        
        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setCustomAuthFailHandler(customAuthFailHandler);
        smsCodeFilter.setConfigProperties(configProperties);
        
        //处理加载信息
        verificationCodeFilter.afterPropertiesSet();
        smsCodeFilter.afterPropertiesSet();
        
        http.addFilterBefore(verificationCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                //表单登录,本版本默认方式
                .formLogin()
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
        //记住我
        http.rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(configProperties.getRememberMeSeconds())
                //拿到username后去做登录
                .userDetailsService(userDetailsService);
        //---------------------------------------------------------------------------------------------
        //关闭跨站请求
        http.csrf().disable();
        //---------------------------------------------------------------------------------------------
        //对任何请求进行认证
        //对请求授权,
        authorizedConfigManager.config(http.authorizeRequests());
        //---------------------------------------------------------------------------------------------
        http.apply(smsCodeAuthenticatiionConfiguration);
        
    }
}
