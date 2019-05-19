package cn.njyazheng.core.verify.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class VerificationCode {
    private BufferedImage bufferedImage;
    private String code;
    //验证码过期时间
    private LocalDateTime expireTime;
    public VerificationCode(){}
    
    /**
     *
     * @param bufferedImage
     * @param code
     * @param time 过期时间段,秒
     */
    public VerificationCode(BufferedImage bufferedImage, String code,  LocalDateTime time) {
        this.bufferedImage = bufferedImage;
        this.code = code;
        this.expireTime = time;
    }
    public boolean isExpire(){
        return LocalDateTime.now().isAfter(expireTime);
    }
    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
    
    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
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
