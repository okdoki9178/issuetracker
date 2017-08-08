package com.lbj.issuetracker.user.security;

import com.lbj.issuetracker.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

/**
 * Created by LBJ on 2017-07-27.
 */

@Data
public class LoginUserDetails extends org.springframework.security.core.userdetails.User {
    private final User user;

    public LoginUserDetails(User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList("USER"));
        this.user = user;
    }
}
