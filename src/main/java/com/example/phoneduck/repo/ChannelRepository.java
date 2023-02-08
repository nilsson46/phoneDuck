package com.example.phoneduck.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.channels.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
