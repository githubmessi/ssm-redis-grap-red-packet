package com.ssm.chapter20.controller;

import com.ssm.chapter20.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/userRedPacket")
@Controller
public class UserRedPacketController {

    @Autowired
    private UserRedPacketService userRedPacketService;


    @RequestMapping("/grapRedPacket")
    @ResponseBody
    public Map<String, Object> grapRedPacket(Long redPacketId, Long userId){
        //抢红包
        int result = this.userRedPacketService.grapRedPacket(redPacketId, userId);
        Map<String, Object> retMap = new HashMap<>();
        boolean flag = result > 0;
        retMap.put("success", flag);
        retMap.put("message", flag ? "抢红包成功" : "抢红包失败");
        return retMap;
    }

    @RequestMapping("/hello")
    @ResponseBody
    public Map<String, Object> hello(){
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("hello", "tom and jerry.");
        System.out.println("添加1");
        System.out.println("添加2");
        return retMap;
    }
}
