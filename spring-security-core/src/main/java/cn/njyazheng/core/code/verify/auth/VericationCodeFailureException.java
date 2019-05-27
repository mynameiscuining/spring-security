package cn.njyazheng.core.code.verify.auth;

import org.springframework.security.core.AuthenticationException;

public class VericationCodeFailureException extends AuthenticationException {
    public VericationCodeFailureException(String explanation) {
        super(explanation);
    }
}
