package cn.njyazheng.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
//定制认证失败后的行为
public class CustomAuthFailHandler implements AuthenticationFailureHandler {
    private static final Logger logger= LoggerFactory.getLogger(CustomAuthFailHandler.class);
    
    @Autowired
    private ObjectMapper objectMapper;
    /**
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param authenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException authenticationException) throws IOException, ServletException {
        logger.info("--------------------------登录失败");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        //状态码设置
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authenticationException));
    }
}
