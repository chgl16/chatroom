package xyz.cglzwz.chatroom.service;

import xyz.cglzwz.chatroom.domain.SysUser;

public interface RegisterService {
    /**
     * 用户注册
     *
     * @param sysUser
     * @return false-失败、已经注册， true-注册成功
     */
    public boolean userRegister(SysUser sysUser);
}
