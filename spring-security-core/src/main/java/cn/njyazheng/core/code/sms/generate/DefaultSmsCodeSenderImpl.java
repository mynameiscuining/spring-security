package cn.njyazheng.core.code.sms.generate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSmsCodeSenderImpl implements SmsCodeSender {
    
    private static final Logger logger= LoggerFactory.getLogger(DefaultSmsCodeSenderImpl.class);
    @Override
    public void send(String mobile, String code) {
       logger.info("-------------------向通讯接口发送短信");
       logger.info("-------------------手机号:"+mobile);
       logger.info("-------------------验证码:"+code);
    }
}
