package cn.njyazheng.core.auth;

import cn.njyazheng.core.LoginType;
import cn.njyazheng.core.ConfigProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
//SavedRequestAwareAuthenticationSuccessHandler默认认证的成功后的处理器
public class CustomAuthSuccessHandler2 extends SavedRequestAwareAuthenticationSuccessHandler {
    
    @Autowired
    private ConfigProperties configProperties;
    
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
    
        logger.info("--------------------------登录成功");
        if (LoginType.JSON.equals(configProperties.getBrowser().getLoginType())){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else {
            //默认跳到原来访问的url
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
