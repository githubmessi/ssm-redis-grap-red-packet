package com.ssm.chapter20.test;


import com.ssm.chapter20.config.RootConfig;
import com.ssm.chapter20.config.WebConfig;
import com.ssm.chapter20.mapper.RedPacketMapper;
import com.ssm.chapter20.pojo.RedPacket;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MapperTest {

    @Test
    public void test1(){
        AnnotationConfigApplicationContext context = null;
        try {
            context = new AnnotationConfigApplicationContext(RootConfig.class, WebConfig.class);
            RedPacketMapper redPacketMapper = context.getBean(RedPacketMapper.class);
            RedPacket redPacketById = redPacketMapper.getRedPacketById(1L);
            System.out.println(redPacketById);
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            context.close();
        }
    }
}
