package cn.njyazheng.core.code.sms.auth;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 这里参考的是UsernamePasswordAuthenticationFilter
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public static final String CUSTOM_SECURITY_FORM_MOBILE_KEY = "mobile";
    private String mobileParameter = CUSTOM_SECURITY_FORM_MOBILE_KEY;
    //是否只处理post请求
    private boolean postOnly = true;
    
    public SmsCodeAuthenticationFilter() {
        //当前过滤器处理的请求是什么
        super(new AntPathRequestMatcher("/authentication/mobile", "POST"));
    }
    
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //判断是不是post请求
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String mobile = this.obtainMobile(request);
            if (mobile == null) {
                mobile = "";
            }
    
            mobile = mobile.trim();
            //实例化token
            SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);
            //请求信息也设置到token里面去
            this.setDetails(request, authRequest);
            //传入token
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }
    
    
    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(this.mobileParameter);
    }
    
    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
    
    public void setMobileParameter(String mobileParameter) {
        Assert.hasText(mobileParameter, "mobile parameter must not be empty or null");
        this.mobileParameter = mobileParameter;
    }
    
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
    
    public final String getMobileParameter() {
        return this.mobileParameter;
    }
}
