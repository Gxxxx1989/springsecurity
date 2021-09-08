package com.guoxi.springsecurity.login;

import com.guoxi.springsecurity.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author guoxi
 */
@Service
public class LoginServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserInfo loadUserByUsername(String s) throws UsernameNotFoundException {
        //自定义登录逻辑 查询mysql
        UserInfo userInfo=new UserInfo();
        userInfo.setUsername("guoxi");
        userInfo.setPassword(encoder.encode("123456"));
        System.out.println("登录中。。。");
        return userInfo;
    }
}
