package cn.njyazheng.core.auth.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
@Order(Integer.MAX_VALUE)
public class RBACAuthorizedConfigProvider implements AuthorizedConfigProvider {
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        //应该放到最后,不然都会走这个,解决方法指定顺序
        config.anyRequest().access("@defaultRbacService.hasPermission( servletRequest,authentication)");
    }
}
