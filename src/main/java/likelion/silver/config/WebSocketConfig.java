package likelion.silver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // 1. 클라이언트가 메시지를 보내는 주소 prefix
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // 구독 prefix
        config.setApplicationDestinationPrefixes("/app"); // 메시지 보낼 prefix
    }

    // 2. WebSocket 엔드포인트 설정 (프론트에서 연결할 주소)
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-chat")  // React나 HTML에서 여기에 연결
                .setAllowedOriginPatterns("*")
                .withSockJS(); // SockJS fallback
    }
}
