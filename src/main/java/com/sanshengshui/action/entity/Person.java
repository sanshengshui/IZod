package com.sanshengshui.action.entity;

/**
 * @author 穆书伟
 * @description 示例JavaBean
 * @date 2018年1月29日 下午13:28
 */
public class Person {
    private String name;
    private Integer age;

    public Person(){
        super();
    }
    public Person(String name,Integer age){
        super();
        this.name = name;
        this.age = age;
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
}
