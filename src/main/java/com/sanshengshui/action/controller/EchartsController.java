package com.sanshengshui.action.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 穆书伟
 * @date 2018年1月29日 下午17:03
 * @description 做Echarts页面跳转
 */
@Controller
public class EchartsController {

    @RequestMapping("/echarts")
    public String echarts(Model model){
        return "echarts";
    }
}
