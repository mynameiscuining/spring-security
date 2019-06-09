package cn.njyazheng.core.rbac;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface RbacService {
    boolean hasPermission(HttpServletRequest servletRequest, Authentication authentication);
}
