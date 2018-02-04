package com.sanshengshui.action.dao;

import com.sanshengshui.action.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 穆书伟
 * @description 实体类Repository
 * @date 2018年2月4日 下午13::37
 */
public interface ComputerRepository extends JpaRepository<Computer,Long>{
}
