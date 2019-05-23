package cn.njyazheng.core.code.sms;

import cn.njyazheng.core.code.CheckCode;

import java.time.LocalDateTime;

public class SmsCode extends CheckCode {
    private String code;
    private LocalDateTime expireTime;
    public SmsCode(){}
    public SmsCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }
    public boolean isExpire(){
        return LocalDateTime.now().isAfter(expireTime);
    }
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public LocalDateTime getExpireTime() {
        return expireTime;
    }
    
    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
