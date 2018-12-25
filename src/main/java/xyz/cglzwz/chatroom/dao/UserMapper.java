package xyz.cglzwz.chatroom.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.cglzwz.chatroom.domain.SysUser;

/**
 * 映射器接口
 *
 * @author chgl16
 * @date 2018-12-20 12:27
 * @version 1.0
 */

@Repository
public interface UserMapper {
    /**
     * 通过用户名查找该用户
     *
     * @param username
     * @return
     */
    public SysUser findByUsername(@Param("username") String username);

    /**
     * 注册一位用户
     *
     * @param sysUser
     */
    public void insertUser(SysUser sysUser);
}
