package com.sanshengshui.action.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * @author 穆书伟
 * @description 实体类
 * @date 2018年2月2日 下午16:14
 */
@NamedQuery(name = "People.withNameAndAddressNamedQuery",
query = "select p from People p where p.name =?1 and address =?2")
@Entity
public class People {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer age;
    private String address;

    public People(){
        super();
    }
    public People(Long id,String name,Integer age,String address){
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }
    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
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
