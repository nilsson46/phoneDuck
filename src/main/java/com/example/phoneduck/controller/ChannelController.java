package com.example.phoneduck.controller;

import com.example.phoneduck.model.Channel;
import com.example.phoneduck.service.ChannelService;
import com.example.phoneduck.wesocket.ChannelSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.swing.*;
import java.util.List;

@RestController
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private ChannelSocketHandler channelSocketHandler;

    private List<Channel> channelList;

    @GetMapping("channels")
    public ResponseEntity<List<Channel>> getChannel(){
        List<Channel> channelList = channelService.getAll();
        return ResponseEntity.ok(channelList);
    }

    @PostMapping("channels")
    public  ResponseEntity<List<Channel>> createChannel(@RequestBody Channel channel){
        channelService.save(channel);
        channelSocketHandler.broadcast(channel.getTitle() + " channel was created");
        return ResponseEntity.status(201).body(channelList);
    }

    @DeleteMapping("channels/{id}")
    public ResponseEntity<List<Channel>> deleteChannel(@PathVariable long id) {
        channelService.delete(id);
        channelSocketHandler.broadcast(channelService.getAll().toString());
        return getChannel();
    }
}
