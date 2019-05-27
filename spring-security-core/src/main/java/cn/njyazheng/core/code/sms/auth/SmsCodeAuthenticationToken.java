package cn.njyazheng.core.code.sms.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

//作用:封装登录信息
//认证之前封装的是手机号
//认证之后存放的是用户详细信息
//以下代码copy-修改security的用户密码认证UsernamePasswordAuthenticationToken
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    
    // ~ Instance fields
    // ================================================================================================
    //存放认证信息的,成功之后存放用户信息的
    private final Object principal;
    
    // ~ Constructors
    // ===================================================================================================
    
    /**
     * This constructor can be safely used by any code that wishes to create a
     * <code>UsernamePasswordAuthenticationToken</code>, as the {@link #isAuthenticated()}
     * will return <code>false</code>.
     *
     */
    public SmsCodeAuthenticationToken(String mobile) {
        super(null);
        //存放的是手机号
        this.principal = mobile;
        //指的是没登录
        setAuthenticated(false);
    }
    
    /**
     * This constructor should only be used by <code>AuthenticationManager</code> or
     * <code>AuthenticationProvider</code> implementations that are satisfied with
     * producing a trusted (i.e. {@link #isAuthenticated()} = <code>true</code>)
     * authentication token.
     *
     * @param principal
     * @param authorities
     */
    public SmsCodeAuthenticationToken(Object principal,
                                               Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        //存放认证用户信息
        this.principal = principal;
        //认证成功之后
        super.setAuthenticated(true); // must use super, as we override
    }
    
    // ~ Methods
    // ========================================================================================================
    
    public Object getCredentials() {
        return null;
    }
    
    public Object getPrincipal() {
        return this.principal;
    }
    
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        
        super.setAuthenticated(false);
    }
    
    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
