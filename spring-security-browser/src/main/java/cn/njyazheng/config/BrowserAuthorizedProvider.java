package cn.njyazheng.config;

import cn.njyazheng.core.auth.config.AuthorizedConfigProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

public class BrowserAuthorizedProvider implements AuthorizedConfigProvider {
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers("/authentication/require", "/verify/code", "/sms/code", "/error", "/js/**.js","/authentication/mobile").permitAll();
    }
}
