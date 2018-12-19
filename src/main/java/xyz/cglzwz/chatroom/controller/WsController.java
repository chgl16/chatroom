package xyz.cglzwz.chatroom.controller;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 用来给浏览器发信息
     */
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * principal是Spring Security提供的，对应其默认提供的登录页面
     * msg对应的是客户端的text，WebSocket 这里可以不同名映射
     *
     * @param principal
     * @param msg
     */
    @MessageMapping(value = "/chat")
    public void handleChat(Principal principal, String msg) {
        log.info("发送者 principal: " + principal.toString());
        log.info("发送的信息: : " + msg);
        if (true) {
            simpMessagingTemplate.convertAndSendToUser("b", "/notification", "a: " + msg);
        }
    }

    @RequestMapping(value = "/index")
    public void toChat() {
        log.info("跳转到聊天首界面");
//        return "index";
    }

    @GetMapping(value = "/login")
    public String toLogin() {
//        log.info("model: " + model.toString());
//        log.info("error: " + error);
        log.info("跳转到登录页面");
        return "login";
    }
//
//    @PostMapping(value = "/login")
//    public String login(@RequestParam("username") String username,  @RequestParam("password") String password) {
//        log.info("用户信息：" + username + ", " + password);
//        if (username.equals("lin") && password.equals("123")) {
//            return "chat";
//        }
//        else
//            return "register";
//    }
}
