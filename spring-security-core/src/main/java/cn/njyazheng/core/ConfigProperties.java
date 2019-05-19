package cn.njyazheng.core;

import cn.njyazheng.core.browser.BrowserConfigProperties;
import cn.njyazheng.core.verify.code.VerificationCodeProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "custom.security", ignoreInvalidFields = true)
public class ConfigProperties {
    private BrowserConfigProperties browser;
    private VerificationCodeProperties verify;
    
    public BrowserConfigProperties getBrowser() {
        return browser;
    }
    
    public void setBrowser(BrowserConfigProperties browser) {
        this.browser = browser;
    }
    
    public VerificationCodeProperties getVerify() {
        return verify;
    }
    
    public void setVerify(VerificationCodeProperties verify) {
        this.verify = verify;
    }
}
