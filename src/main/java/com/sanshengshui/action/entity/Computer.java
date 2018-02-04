package com.sanshengshui.action.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 穆书伟
 * @description 用于演示@Transactional使用
 * @date 2018年2月4日 下午13:18
 */
@Entity
@Table(name = "people")
public class Computer {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer age;
    private String address;

    public Computer(){
        super();
    }

    public Computer(Long id,String name,Integer age,String address){
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
