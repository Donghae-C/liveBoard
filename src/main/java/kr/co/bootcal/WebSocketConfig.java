package kr.co.bootcal;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@AllArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    private final TestChatHandler testChatHandler;
    private final TestBoardHandler testBoardHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        HttpSessionInterceptor interceptor = new HttpSessionInterceptor();
        registry.addHandler(testChatHandler, "/test").setAllowedOrigins("*").addInterceptors(interceptor);
        registry.addHandler(testBoardHandler, "/testBoardMain").setAllowedOrigins("*").addInterceptors(interceptor);
    }
}
