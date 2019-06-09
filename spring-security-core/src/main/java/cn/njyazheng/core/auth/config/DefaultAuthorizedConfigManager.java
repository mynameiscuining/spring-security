package cn.njyazheng.core.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DefaultAuthorizedConfigManager implements AuthorizedConfigManager {
    @Autowired
    private List<AuthorizedConfigProvider> authorizedConfigProviders;
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        authorizedConfigProviders.stream().forEach(authorizedConfigProvider -> {
            authorizedConfigProvider.config(config);
        });
        //会覆盖掉之前的anyRequest()配置
        config.anyRequest().authenticated();
    }
}
