package com.lbj.issuetracker.user.config;

import com.lbj.issuetracker.user.security.LoginUserDetails;
import com.sun.deploy.net.HttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by LBJ on 2017-08-08.
 */
@Component
public class HttpLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        if(authentication == null){
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }else{
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
            PrintWriter printWriter = httpServletResponse.getWriter();
            printWriter.write(loginUserDetails.getUsername()+" logout");
            printWriter.flush();
        }
    }
}
