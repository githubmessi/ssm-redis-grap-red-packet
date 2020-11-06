package com.ssm.chapter20.service;

import com.ssm.chapter20.mapper.RedPacketMapper;
import com.ssm.chapter20.mapper.UserRedPacketMapper;
import com.ssm.chapter20.pojo.RedPacket;
import com.ssm.chapter20.pojo.UserRedPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRedPacketServiceImpl implements UserRedPacketService {

    @Autowired
    private RedPacketMapper redPacketMapper;

    @Autowired
    private UserRedPacketMapper userRedPacketMapper;

    public static final int FAILED = 0 ;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int grapRedPacket(Long redPacketId, Long userId) {
        RedPacket redPacket = this.redPacketMapper.getRedPacketById(redPacketId);
        if(redPacket.getStock() > 0){
            this.redPacketMapper.decreaseRedPacket(redPacketId);
            UserRedPacket userRedPacket = new UserRedPacket();
            userRedPacket.setRedPacketId(redPacketId);
            userRedPacket.setUserId(userId);
            userRedPacket.setAmount(redPacket.getUnitAmount());
            userRedPacket.setNote("抢红包" + userId);
            //加入抢红包信息
            int result = this.userRedPacketMapper.grapRedPacket(userRedPacket);
            return result;
        }
        return FAILED;
    }
}
