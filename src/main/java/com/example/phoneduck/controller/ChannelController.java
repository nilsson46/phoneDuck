package com.example.phoneduck.controller;

import com.example.phoneduck.model.Channel;
import com.example.phoneduck.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    private List<Channel> channelList;



    @GetMapping("channels")
    public ResponseEntity<List<Channel>> getChannel(){
        List<Channel> channelList = channelService.getAll();
        return ResponseEntity.ok(channelList);
    }

    @PostMapping("channels")
    public  ResponseEntity<List<Channel>> createChannel(@RequestBody Channel channel){
        channelService.save(channel);

        List<Channel> channelList = channelService.getAll();
        return ResponseEntity.status(201).body(channelList);
    }
}
