package cn.njyazheng.core.browser;


import cn.njyazheng.core.LoginType;

public class BrowserConfigProperties {
    
    private String loginPage="/login.html";
    
    private LoginType loginType=LoginType.JSON;
    
    
    
    public String getLoginPage() {
        return loginPage;
    }
    
    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
    
    public LoginType getLoginType() {
        return loginType;
    }
    
    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
