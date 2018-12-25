package xyz.cglzwz.chatroom.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.cglzwz.chatroom.dao.UserMapper;
import xyz.cglzwz.chatroom.domain.SysUser;
import xyz.cglzwz.chatroom.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {
    private static final Logger log = LoggerFactory.getLogger(RegisterServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public boolean userRegister(SysUser sysUser) {
        if (userMapper.findByUsername(sysUser.getUsername()) == null) {
            log.info("正在给用户注册");
            // 加密密码
            // # 这种不行注意 PasswordEncoder encoder2 = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            BCryptPasswordEncoder encoder = passwordEncoder();
            sysUser.setPassword(encoder.encode(sysUser.getPassword()));
            userMapper.insertUser(sysUser);
            return true;
        }
        log.info("失败--用户已经注册了");
        return false;
    }
}
