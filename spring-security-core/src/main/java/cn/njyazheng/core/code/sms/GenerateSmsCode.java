package cn.njyazheng.core.code.sms;




import javax.servlet.http.HttpServletRequest;

public interface GenerateSmsCode {
     SmsCode generate(HttpServletRequest request);
}
