package cn.njyazheng.core.code.sms.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
    
    private UserDetailsService userDetailsService;
    
    //身份认证的逻辑
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken smsCodeAuthenticationToken=(SmsCodeAuthenticationToken)authentication;
        UserDetails userDetails=userDetailsService.loadUserByUsername((String) smsCodeAuthenticationToken.getPrincipal());
        if (userDetails==null){
            throw new InternalAuthenticationServiceException("没有找到用户信息");
        }
        //用户的信息和权限
        SmsCodeAuthenticationToken smsCodeAuthenticationResult=new SmsCodeAuthenticationToken(userDetails,userDetails.getAuthorities());
        smsCodeAuthenticationResult.setDetails(smsCodeAuthenticationToken.getDetails());
        return smsCodeAuthenticationResult;
    }
    //AuthenticationManager根据support方法判断使用哪个provider
     //当传入token进来的时候,找到对应的provider
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
    
    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }
    
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
