package cn.njyazheng.core;

import cn.njyazheng.core.browser.BrowserConfigProperties;
import cn.njyazheng.core.verify.code.VerificationCodeProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "custom.security", ignoreInvalidFields = true)
public class ConfigProperties {
    private BrowserConfigProperties browser;
    private VerificationCodeProperties verify;
    //一个月
    private int rememberMeSeconds=2592000;
    public BrowserConfigProperties getBrowser() {
        return browser;
    }
    
    public void setBrowser(BrowserConfigProperties browser) {
        this.browser = browser;
    }
    
    public VerificationCodeProperties getVerify() {
        return verify;
    }
    
    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }
    
    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
    
    public void setVerify(VerificationCodeProperties verify) {
        this.verify = verify;
    }
}
