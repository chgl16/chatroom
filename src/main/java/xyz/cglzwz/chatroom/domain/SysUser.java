package xyz.cglzwz.chatroom.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 实现了UserDetails接口，UserDetails是SpringSecurity验证框架内部提供的用户验证接口，
 * 主要是来完成自定义用户认证功能，需要实现getAuthorities方法内容，将定义的角色列表添加到授权的列表内。
 *
 * @author chgl16
 * @date 2018-12-19 23:56
 * @version 1.0
 */

@Component
public class SysUser {
    private Integer id;
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SysUser: [id: " + id + ", username: " + username + ", password: " + password + "]";
    }
}
