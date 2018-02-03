package com.sanshengshui.action.controller;

import com.sanshengshui.action.dao.PeopleRepository;
import com.sanshengshui.action.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 穆书伟
 * @date 2018年2月2日 下午17:26
 * @descripiton 数据控制器类
 */
@RestController
@RequestMapping("/people")
public class DataController {
    @Autowired
    PeopleRepository peopleRepository;

    @RequestMapping("/save")
    public People save(String name,String address,Integer age){
        People p = peopleRepository.save(new People(null,name,age,address));
        return p;
    }

    /**
     * @description 测试findByAddress
     * @param address
     * @return
     */
    @RequestMapping("q1")
    public List<People> q1(String address){
        List<People> people = peopleRepository.findByAddress(address);
        return people;
    }

    /**
     *测试findByNameAndAddress
     */

    @RequestMapping("q2")
    public People q2(String name,String address){
        People people = peopleRepository.findByNameAndAddress(name,address);
        return people;
    }

    /**
     *测试withNameAndAddressQuery
     */
    @RequestMapping("q3")
    public People q3(String name,String address){
        People people = peopleRepository.withNameAndAddressQuery(name,address);
        return people;
    }
    /**
     * 测试withNameAndAddressNamedQuery
     */
    @RequestMapping("q4")
    public List<People> q4(String name,String address){
        List<People> people = peopleRepository.withNameAndAddressNamedQuery(name,address);
        return people;
    }

    /**
     * 测试排序
     */
    @RequestMapping("/sort")
    public List<People> sort(){
        List<People> people = peopleRepository.findAll(new Sort(Sort.Direction.ASC,"age"));
        return people;
    }

    /**
     * 测试分页
     */
    @RequestMapping("/page")
    public Page<People> page(){
        Page<People> pagePeople = peopleRepository.findAll(new PageRequest(1,2));
        return pagePeople;
    }

}
