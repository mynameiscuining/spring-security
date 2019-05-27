package cn.njyazheng.core.code.sms.generate;


import java.util.HashSet;
import java.util.Set;

public class SmsCodeProperties {
    private Set<String> codeUrls=new HashSet<>();
    private Integer expire=1;
    
    public Integer getExpire() {
        return expire;
    }
    
    public void setExpire(Integer expire) {
        this.expire = expire;
    }
    
    public Set<String> getCodeUrls() {
        return codeUrls;
    }
    
    public void setCodeUrls(Set<String> codeUrls) {
        this.codeUrls = codeUrls;
    }
}
