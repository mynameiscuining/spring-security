package cn.njyazheng.core.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//定制认证成功后的行为,默认的行为会跳转到原来请求的地址上
@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger logger= LoggerFactory.getLogger(CustomAuthSuccessHandler.class);
    
    @Autowired
    private ObjectMapper objectMapper;
    /**
     *登录成功之后就会被调用
     * @param httpServletRequest
     * @param httpServletResponse
     * @param authentication 也是security的核心接口,封装认证信息,包含请求对的IP,session,和认证通过后的userdetails,
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info("--------------------------登录成功");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
    }
}
