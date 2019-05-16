package cn.njyazheng.auth;

import cn.njyazheng.config.Browserproperties;
import cn.njyazheng.core.LoginType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
//SimpleUrlAuthenticationFailureHandler是security默认认证失败后的行为
public class CustomAuthFailHandler2 extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Browserproperties browserproperties;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    
        logger.info("--------------------------登录失败");
        if (LoginType.JSON.equals(browserproperties.getBrowser().getLoginType())){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(exception));
        }else {
            //默认跳到页面上
            super.onAuthenticationFailure(request, response, exception);
        }
       
    }
}
