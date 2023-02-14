package com.example.phoneduck.wesocket;

import com.example.phoneduck.model.Channel;
import com.example.phoneduck.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ChatSocketHandler extends TextWebSocketHandler {
    @Autowired
    ChannelService channelService;

    private List<WebSocketSession> sessions = new ArrayList<>();


    public void broadcast(String channel, String message) {
        try {
            for (WebSocketSession webSocketSession : sessions) {
                String room = Objects.requireNonNull(webSocketSession.getHandshakeHeaders().getFirst("room"));
                if (channel.contains(room)) {
                    webSocketSession.sendMessage(new TextMessage("Message: " + message));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) throws Exception {
        String room = Objects.requireNonNull(webSocketSession.getHandshakeHeaders().getFirst("room"));
        broadcast(room, message.getPayload());
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        String listOfActiveChannels = channelService.getAll().toString();
        //System.out.println(channelService.getAll().toString());
        // System.out.println(webSocketSession.getHandshakeHeaders().getFirst("room"));
        //String room = Objects.requireNonNull(webSocketSession.getHandshakeHeaders().get("room")).toString();
        String room = webSocketSession.getHandshakeHeaders().getFirst("room");
        if (listOfActiveChannels.contains(room)) {
            webSocketSession.sendMessage(new TextMessage("Welcome to the channel, send a message to see if anyone is active"));
            sessions.add(webSocketSession);
            System.out.println("New chat session started");
        } else {
            throw new IllegalStateException("This channel is not active");
        }
    }


    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        sessions.remove(webSocketSession);
        System.out.println("Chat session was removed");
    }
}

