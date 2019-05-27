package cn.njyazheng.core.code;

import cn.njyazheng.core.browser.SessionKey;
import cn.njyazheng.core.code.AbstractCheckCodeProcessor;
import cn.njyazheng.core.code.sms.generate.GenerateSmsCode;
import cn.njyazheng.core.code.sms.generate.SmsCode;
import cn.njyazheng.core.code.sms.generate.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletRequest;

@Component("smsCodeProcessor")
public class SmsCodeProcessor extends AbstractCheckCodeProcessor<SmsCode> {
    @Autowired
    private SmsCodeSender smsCodeSender;
    
    @Autowired
    private GenerateSmsCode generateSmsCode;
    
    @Override
    protected void save(ServletWebRequest servletWebRequest, SmsCode validateCode) {
        servletWebRequest.getRequest().getSession().setAttribute(SessionKey.SESSION_KEY_SMS_CODE, validateCode);
    }
    
    @Override
    protected SmsCode generate(ServletWebRequest servletWebRequest) {
        return generateSmsCode.generate(servletWebRequest.getRequest());
    }
    
    @Override
    protected void send(ServletWebRequest servletWebRequest, SmsCode validateCode) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter((ServletRequest) servletWebRequest, "mobile");
        smsCodeSender.send(mobile, validateCode.getCode());
    }
}
