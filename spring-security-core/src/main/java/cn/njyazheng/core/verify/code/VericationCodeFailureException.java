package cn.njyazheng.core.verify.code;

import org.springframework.security.core.AuthenticationException;

public class VericationCodeFailureException extends AuthenticationException {
    public VericationCodeFailureException(String explanation) {
        super(explanation);
    }
}
