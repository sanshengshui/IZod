package com.sanshengshui.action.dao;

import com.sanshengshui.action.entity.Computer;

/**
 * @author 穆书伟
 * @description 接口
 */
public interface CacheDemoService {
    Computer save(Computer computer);
    void remove(Long id);
    Computer findOne(Computer computer);
}
