package com.sanshengshui.action.entity;

import java.io.Serializable;

/**
 * @author 穆书伟
 * @date 2018年2月5日 上午11:57
 * @description 领域模型类
 */
public class RedisPerson implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private Integer age;

    public RedisPerson(){
        super();
    }

    public RedisPerson(String id,String name,Integer age){
        super();
        this.id = id;
        this.name = name;
        this.age = age;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
