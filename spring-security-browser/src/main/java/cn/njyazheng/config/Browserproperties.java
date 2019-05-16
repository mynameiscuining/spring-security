package cn.njyazheng.config;

import cn.njyazheng.core.browser.BrowserConfigProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("custom.security")
public class Browserproperties {
   private BrowserConfigProperties browser;
    
    public BrowserConfigProperties getBrowser() {
        return browser;
    }
    
    public void setBrowser(BrowserConfigProperties browser) {
        this.browser = browser;
    }
}
