package com.sanshengshui.action.dao.impl;

import com.sanshengshui.action.entity.RedisPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author 穆书伟
 * @description 数据访问
 * @date 2018年2月5日
 */
@Repository
public class PersonDao {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String,String> valOpsStr;

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<Object,Object> valOps;

    public void stringRedisTemplateDemo(){
        valOpsStr.set("xx","yy");
    }
    public void save(RedisPerson redisPerson){
        valOps.set(redisPerson.getId(),redisPerson);
    }
    public String getString(){
        return valOpsStr.get("xx");
    }
    public RedisPerson getPerson(){
        return (RedisPerson) valOps.get("1");
    }

}
