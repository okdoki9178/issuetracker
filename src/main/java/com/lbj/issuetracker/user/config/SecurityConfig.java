package com.lbj.issuetracker.user.config;

import com.lbj.issuetracker.user.security.LoginUserDetails;
import com.lbj.issuetracker.user.security.LoginUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by LBJ on 2017-07-27.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthFailureHandler authFailureHandler;

    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    private HttpAuthenticationEntryPoint httpAuthenticationEntryPoint;

    @Autowired
    private HttpLogoutSuccessHandler httpLogoutSuccessHandler;



    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**","/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // authentication 엔트리포인트 설정
        http.exceptionHandling().authenticationEntryPoint(httpAuthenticationEntryPoint);

        http.csrf().disable();


        http.formLogin()
                .permitAll()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
                .and()
                .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
                .logoutSuccessHandler(httpLogoutSuccessHandler)
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .and()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/v1/feed").permitAll()
                .antMatchers(HttpMethod.GET,"/applink").permitAll()
                .antMatchers(HttpMethod.GET,"/loginForm").permitAll()
                .anyRequest().authenticated();

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Configuration
    static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
        @Autowired
        LoginUserDetailsService userDetailsService;

        @Bean
        PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        public void init (AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder());
        }

    }

}
