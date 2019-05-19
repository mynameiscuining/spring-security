package cn.njyazheng.core.verify.code;

import java.util.*;

public class VerificationCodeProperties {
    private Map<String,String> codeParams=new HashMap<>();
    private Set<String> codeUrls=new HashSet<>();
    
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
