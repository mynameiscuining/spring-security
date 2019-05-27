package cn.njyazheng.core.code.sms.generate;

public interface SmsCodeSender {
    void send(String mobile,String code);
}
