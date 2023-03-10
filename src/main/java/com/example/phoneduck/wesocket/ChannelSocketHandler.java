package com.example.phoneduck.wesocket;

import com.example.phoneduck.controller.ChannelController;
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

@Component
public class ChannelSocketHandler extends TextWebSocketHandler {
@Autowired
    ChannelService channelService;
    private List<WebSocketSession> sessions = new ArrayList<>();
    public void broadcast(String message) {
    try{
        for(WebSocketSession webSocketSession : sessions){
            webSocketSession.sendMessage(new TextMessage("Message: " + message));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    }

    @Override
    protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage textMessage) throws Exception{
        broadcast(textMessage.getPayload());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception{
        sessions.add(webSocketSession);
        webSocketSession.sendMessage(new TextMessage("Welcome, here is all active channels: " + channelService.getAll()));
        System.out.println("New session started");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception{
        sessions.remove(webSocketSession);
        System.out.println("Session was removed");
    }
}

