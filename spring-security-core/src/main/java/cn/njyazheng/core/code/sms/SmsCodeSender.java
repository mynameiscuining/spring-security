package cn.njyazheng.core.code.sms;

public interface SmsCodeSender {
    void send(String mobile,String code);
}
