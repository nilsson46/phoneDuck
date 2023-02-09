package com.example.phoneduck.config;

import com.example.phoneduck.wesocket.ChannelSocketHandler;
import com.example.phoneduck.wesocket.ChatSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class SocketConfig implements WebSocketConfigurer {
    @Autowired
    private ChannelSocketHandler channelSocketHandler;
    @Autowired
    ChatSocketHandler chatSocketHandler;

    private final static String SOCKET_PREFIX = "/sub";
    private final static String SOCKET_CHANNEL = "/channels";
    private final static String SOCKET_CHAT = "/chat";

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler((WebSocketHandler) channelSocketHandler,SOCKET_PREFIX + SOCKET_CHANNEL);
        registry.addHandler((WebSocketHandler) chatSocketHandler, SOCKET_PREFIX + SOCKET_CHAT);
    }
}
