package cn.njyazheng.core.code.sms;


public class SmsCodeProperties {
    private String url;
    private Integer expire=1;
    
    public Integer getExpire() {
        return expire;
    }
    
    public void setExpire(Integer expire) {
        this.expire = expire;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
}
