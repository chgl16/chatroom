package xyz.cglzwz.chatroom.controller;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.cglzwz.chatroom.domain.Message;
import xyz.cglzwz.chatroom.domain.SysUser;
import xyz.cglzwz.chatroom.service.RegisterService;

import java.security.Principal;

/**
 * 控制器
 *
 * @author chgl16
 * @date 2018-12-16 16:32
 * @version 1.0
 */

@Controller
public class WsController {
    private static final Log log = LogFactory.getLog(WsController.class);

    @Autowired
    private RegisterService registerService;

    /**
     * 用来给浏览器发信息
     */
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * principal是Spring Security提供的，对应其默认提供的登录页面
     * Message是一个 POJO，处理前端发生的json,不知道为啥这里用两个单独的参数（receiver和text为啥会获取到整个 json）
     *
     * @param principal
     * @param message
     */
    @MessageMapping(value = "/chat")
    public void handleChat(Principal principal, Message message) {
        log.info("服务器正在转发一条消息--------------------");
        log.info("消息发送者: " + principal.getName());
        log.info("消息接收者: " + message.getReceiver());
        log.info("消息的内容: " + message.getText());
        if (message.getReceiver().equals("")) {
            log.info("不填接收者，这是广播");
            simpMessagingTemplate.convertAndSendToUser("", "/notification",
                    "@广播(" + principal.getName() + "): " + message.getText());
        } else {
            simpMessagingTemplate.convertAndSendToUser(message.getReceiver(), "/notification",
                    principal.getName() + ": " + message.getText());
        }
        log.info("服务器完成该转发-------------------------");
    }

    @RequestMapping(value = "/index")
    public String toChat() {
        log.info("跳转到聊天首界面");
        return "index";
    }

    @GetMapping(value = "/login")
    public String toLogin() {
        log.info("跳转到登录页面");
        return "login";
    }

    @Deprecated
    @PostMapping("/user/login")
    @ResponseBody
    public String userLogin(SysUser sysUser) {
        log.info("注册接受到的的前端用户名密码 username:" + sysUser.getUsername() + ", password: " + sysUser.getPassword());
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/user/register")
    @ResponseBody
    public boolean UserRegister(SysUser sysUser) {
        log.info("注册接受到的的前端用户名密码 username:" + sysUser.getUsername() + ", password: " + sysUser.getPassword());
        return registerService.userRegister(sysUser);
    }

}