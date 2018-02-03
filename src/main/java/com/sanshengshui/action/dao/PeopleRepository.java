package com.sanshengshui.action.dao;

import com.sanshengshui.action.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author 穆书伟
 * @date 2018年2月2日 下午17:08
 * @description 数据访问接口
 */
public interface PeopleRepository extends JpaRepository<People,Long>{
    List<People> findByAddress(String name);

    People findByNameAndAddress(String name,String address);

    @Query("select p from People p where p.name = :name and p.address= :address")
    People withNameAndAddressQuery(@Param("name") String name,@Param("address")String address);

    List<People> withNameAndAddressNamedQuery(String name,String address);
}
