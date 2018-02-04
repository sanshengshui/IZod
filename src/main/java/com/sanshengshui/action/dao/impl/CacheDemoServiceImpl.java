package com.sanshengshui.action.dao.impl;

import com.sanshengshui.action.dao.CacheDemoService;
import com.sanshengshui.action.dao.ComputerRepository;
import com.sanshengshui.action.entity.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author 穆书伟
 * @date 2018年2月4日 下午15:09
 */
@Service
public class CacheDemoServiceImpl implements CacheDemoService{
    @Autowired
    ComputerRepository computerRepository;

    @Override
    @CachePut(value = "people",key = "#computer.id")
    public Computer save(Computer computer) {
        Computer com = computerRepository.save(computer);
        System.out.println("为id,key为:"+com.getId()+"数据做了缓存");
        return com;
    }

    @Override
    @CacheEvict(value = "people")
    public void remove(Long id) {
        System.out.println("删除了id,key为"+id+"的数据缓存");
        computerRepository.delete(id);
    }

    @Override
    @Cacheable(value = "people",key = "#computer.id")
    public Computer findOne(Computer computer) {
       Computer com = computerRepository.findOne(computer.getId());
       System.out.println("为id,key为:"+ com.getId()+"数据做了缓存");
       return com;
    }
}
