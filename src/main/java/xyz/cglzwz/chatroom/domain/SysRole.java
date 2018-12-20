package xyz.cglzwz.chatroom.domain;

import org.springframework.stereotype.Component;

/**
 * 用户角色
 *
 * @author chgl16
 * @date 2018-12-19 23:50
 * @version 1.0
 */

@Component
public class SysRole {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

