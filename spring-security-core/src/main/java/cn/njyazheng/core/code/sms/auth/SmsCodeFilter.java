package cn.njyazheng.core.code.sms.auth;

import cn.njyazheng.core.ConfigProperties;
import cn.njyazheng.core.auth.CustomAuthFailHandler2;
import cn.njyazheng.core.browser.SessionKey;
import cn.njyazheng.core.code.sms.generate.SmsCode;
import cn.njyazheng.core.code.verify.auth.VericationCodeFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

//OncePerRequestFilter保证过滤器只被调用一次
//InitializingBean等相应的参数初始化完成之后再来初始化本类
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(SmsCodeFilter.class);
    private CustomAuthFailHandler2 customAuthFailHandler;
    private ConfigProperties configProperties;
    private Set<String> codeUrls;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    
    /**
     * InitializingBean 初始化其他属性完成后在此进行初始本类
     *
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        codeUrls = configProperties.getSms().getCodeUrls();
        codeUrls.add("/authentication/mobile");
        
    }
    
    @Autowired
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        logger.info("--------------------------------------请求地址uri:" + httpServletRequest.getRequestURI());
        logger.info("--------------------------------------请求方法method:" + httpServletRequest.getMethod());
        if (codeUrls.stream().filter(codeUrls -> antPathMatcher.match(codeUrls, httpServletRequest.getRequestURI())).count() > 0L) {
            try {
                validateCode(httpServletRequest);
            } catch (VericationCodeFailureException e) {
                customAuthFailHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                e.printStackTrace();
                return;
            }
            
        }
        //如果不符合登录的url,也要调用过滤器链中后面的过滤器
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
    
    private void validateCode(HttpServletRequest httpServletRequest) throws VericationCodeFailureException {
        String code = httpServletRequest.getParameter("mobileCode");
        if (StringUtils.isEmpty(code)) {
            throw new VericationCodeFailureException("请输入短信动态码");
        }
        HttpSession session = httpServletRequest.getSession();
        SmsCode verificationCode = (SmsCode) session.getAttribute(SessionKey.SESSION_KEY_SMS_CODE);
        if(verificationCode==null){
            throw new VericationCodeFailureException("请获取短信动态码");
        }
        if (verificationCode.isExpire()) {
            throw new VericationCodeFailureException("短信动态码已过期");
        }
        if (!code.trim().equalsIgnoreCase(verificationCode.getCode())) {
            throw new VericationCodeFailureException("请输入正确短信动态码");
        }
        session.removeAttribute(SessionKey.SESSION_KEY_SMS_CODE);
    }
    
    public CustomAuthFailHandler2 getCustomAuthFailHandler() {
        return customAuthFailHandler;
    }
    
    public void setCustomAuthFailHandler(CustomAuthFailHandler2 customAuthFailHandler) {
        this.customAuthFailHandler = customAuthFailHandler;
    }
    
    public ConfigProperties getConfigProperties() {
        return configProperties;
    }
    
    public void setConfigProperties(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }
}
