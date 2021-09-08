package com.guoxi.springsecurity.config;

import com.guoxi.springsecurity.login.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author guoxi
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private LoginServiceImpl loginService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService);
        //auth.inMemoryAuthentication().withUser("guoxi2").password(encoder().encode("111")).authorities("admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //认证
        http.formLogin().loginPage("/login.html")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/main.html");
        //授权
        http.authorizeRequests().antMatchers("/login.html", "/user/login").permitAll()
                .anyRequest().authenticated().and().csrf().disable();
    }
}
