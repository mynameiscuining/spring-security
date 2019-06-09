package cn.njyazheng.core.auth.config;

import cn.njyazheng.core.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
@Order(Integer.MIN_VALUE)
public class DefualtAuthorizedConfigProvider implements AuthorizedConfigProvider {
    @Autowired
    private ConfigProperties configProperties;
    
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        //设置登录页不认证
        config.antMatchers(configProperties.getBrowser().getLoginPage()).permitAll();
    }
}
