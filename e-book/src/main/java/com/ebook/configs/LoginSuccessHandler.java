package com.ebook.configs;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        System.out.println(roles.size());
        System.out.println(roles);
        if(roles.contains("ROLE_VENDOR")){
            System.out.println("VENDOR login");
            httpServletResponse.sendRedirect("/vendor");
        }else {
            System.out.println("user login");
            httpServletResponse.sendRedirect("/");
        }
    }
}
