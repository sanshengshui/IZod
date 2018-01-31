package com.sanshengshui.action.controller;

import com.sanshengshui.action.common.WiselyRequest;
import com.sanshengshui.action.common.WiselyResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {
    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public WiselyResponse say(WiselyRequest wiselyRequest) throws InterruptedException {
        Thread.sleep(3000);
        return new WiselyResponse("Welcome, "+ wiselyRequest.getName() + "!");
    }
}
