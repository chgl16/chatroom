package xyz.cglzwz.chatroom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * 配置WebSocket
 *
 * @author chgl16
 * @date 2018-12-16 16:25
 * @version 1.0
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        // 添加一个名为 "/endpointChat" 的基于STOMP子协议的节点（即服务器端socket）
        stompEndpointRegistry.addEndpoint("/endpointChat").withSockJS();
    }
}
