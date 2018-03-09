package com.sanshengshui.action.entity;

import javax.validation.constraints.Size;

/**
 * @author 穆书伟
 * @description 领域模型类
 * @date 2018年1月29日 下午13:28
 */
public class Person {
    /**
     * 此处使用JSR-303注解来效验数据
     */


    @Size(max = 6,min = 2)
    private String name;


    private Integer age;

    private String nation;

    private String address;

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

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
