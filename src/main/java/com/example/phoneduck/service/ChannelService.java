package com.example.phoneduck.service;

import com.example.phoneduck.repo.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.Channel;
import java.util.List;

@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;
    public List<Channel> getAll() { return channelRepository.findAll();
    }
}
