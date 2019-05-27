package cn.njyazheng.core.code.verify.generate;

import java.util.*;

public class VerificationCodeProperties {
    private Map<String,String> codeParams=new HashMap<>();
    private Set<String> codeUrls=new HashSet<>();
    private Integer expire=5;
    
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
    
    public Map<String, String> getCodeParams() {
        return codeParams;
    }
    
    public void setCodeParams(Map<String, String> codeParams) {
        this.codeParams = codeParams;
    }
}
