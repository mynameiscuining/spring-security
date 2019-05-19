package cn.njyazheng.core.verify.code;

import javax.servlet.http.HttpServletRequest;

public interface GenerateVerificationCode {
     VerificationCode generate(HttpServletRequest request);
}
