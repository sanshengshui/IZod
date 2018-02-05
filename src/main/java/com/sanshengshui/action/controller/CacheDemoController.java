package com.sanshengshui.action.controller;

import com.sanshengshui.action.dao.CacheDemoService;
import com.sanshengshui.action.entity.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 穆书伟
 * @date 2018年2月4日 下午16:50
 * @description 控制器
 */
@RestController
public class CacheDemoController {

    @Autowired
    CacheDemoService cacheDemoService;

    @RequestMapping("/put")
    public Computer put(Computer computer){
        return cacheDemoService.save(computer);
    }

    @RequestMapping("/able")
    public Computer cacheable(Computer computer){
        return cacheDemoService.findOne(computer);
    }

    @RequestMapping("evit")
    public String evit(Long id){
        cacheDemoService.remove(id);
        return "ok";
    }
}
