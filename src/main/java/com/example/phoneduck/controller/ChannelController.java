package com.example.phoneduck.controller;

import com.example.phoneduck.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.channels.Channel;
import java.util.List;

@RestController
public class ChannelController {

    private List<Channel> channelList;

    @Autowired
    private ChannelService channelService;

    @GetMapping("channels")
    public ResponseEntity<List<Channel>> getChannel(){
        List<Channel> channelList = channelService.getAll();
        return ResponseEntity.ok(channelList);
    }
}
