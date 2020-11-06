package com.ssm.chapter20.mapper;

import com.ssm.chapter20.pojo.RedPacket;
import org.springframework.stereotype.Repository;

//@Repository
public interface RedPacketMapper {

    //根据id获取红包信息
    RedPacket getRedPacketById(Long id);
    //根据id减口红包库存
    int decreaseRedPacket(Long id);

}
