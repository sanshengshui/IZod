package com.sanshengshui.action.controller;

import com.sanshengshui.action.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 穆书伟
 * @date 2018年1月29日 下午14:06
 * @description 数据准备
 */
@Controller
public class ModelController {

    @RequestMapping("/")
    public String index(Model model){
        Person single = new Person("aa",11);
        List<Person> people = new ArrayList<Person>();
        Person p1= new Person("xx",11);
        Person p2 = new Person("yy",22);
        Person p3 = new Person("zz",33);
        people.add(p1);
        people.add(p2);
        people.add(p3);
        model.addAttribute("singlePerson",single);
        model.addAttribute("people",people);
        return "index";
    }
}
