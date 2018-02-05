package com.sanshengshui.action.controller;

import com.sanshengshui.action.dao.impl.PersonDao;
import com.sanshengshui.action.entity.RedisPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 穆书伟
 * @description 控制器类
 * @date 2018年2月5日 下午14:58
 */
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    PersonDao personDao;

    @RequestMapping("/set")
    public void set(){
        RedisPerson person = new RedisPerson("1","msw",24);
        personDao.save(person);
        personDao.stringRedisTemplateDemo();
    }

    @RequestMapping("/getStr")
    public String getStr(){
        return personDao.getString();
    }

    @RequestMapping("/getPerson")
    public RedisPerson getPerson(){
        return personDao.getPerson();
    }
}
