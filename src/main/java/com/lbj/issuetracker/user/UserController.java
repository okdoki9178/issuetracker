package com.lbj.issuetracker.user;

import com.lbj.issuetracker.user.role.Role;
import com.lbj.issuetracker.user.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by LBJ on 2017-07-27.
 */
@Controller
public class UserController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping("loginForm")
    public String loginForm(){
        return "login";
    }

    @RequestMapping(value = "join", method = RequestMethod.GET)
    public String joinForm(){
        return "join";
    }

    @RequestMapping("/403")
    public String error403() {
        return "/403";
    }

    @RequestMapping(value = "joinUser")
    public String joinUser(@ModelAttribute User user){
//        System.out.println("hello./"+username);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        Role role = new Role();
        role.setUser(user);
        role.setRolename("ROLE_USER");
        roleRepository.save(role);
        return "index";
    }

    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }


    @RequestMapping(value = "who")
    @ResponseBody
    public Object whoIs(Authentication auth){

        return auth.getPrincipal();
    }
}
