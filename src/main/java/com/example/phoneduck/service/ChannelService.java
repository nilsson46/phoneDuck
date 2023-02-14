package com.example.phoneduck.service;

import com.example.phoneduck.model.Channel;
import com.example.phoneduck.repo.JpaChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ChannelService {

    @Autowired
    private JpaChannelRepository jpaChannelRepository;

    public List<Channel> getAll() {
        return jpaChannelRepository.findAll();
    }

    public Channel save(Channel channel) {
        return jpaChannelRepository.save(channel);
    }

    public void delete(long channelId) {
        jpaChannelRepository.deleteById(channelId);
    }
}
