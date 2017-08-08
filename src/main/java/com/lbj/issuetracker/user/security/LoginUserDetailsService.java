package com.lbj.issuetracker.user.security;

import com.lbj.issuetracker.user.User;
import com.lbj.issuetracker.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by LBJ on 2017-07-27.
 */

@Service
public class LoginUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user;
        user = userRepository.findById(username).get();
        System.out.println(user.getUsername());
        if(user == null){
            throw new UsernameNotFoundException("The requested user is no found");
        }

        return new LoginUserDetails(user);
    }
}
