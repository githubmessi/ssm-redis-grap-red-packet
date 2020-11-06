package com.ssm.chapter20.mapper;

import com.ssm.chapter20.pojo.UserRedPacket;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserRedPacketMapper {

    int grapRedPacket(UserRedPacket userRedPacket);
}
