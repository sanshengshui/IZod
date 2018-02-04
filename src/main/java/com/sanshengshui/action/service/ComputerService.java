package com.sanshengshui.action.service;

import com.sanshengshui.action.entity.Computer;

/**
 * @author 穆书伟
 * @description 业务服务Service
 * @date 2018年2月4日 下午13:40
 */
public interface ComputerService {
     Computer saveComputerWithRollBack(Computer computer);
     Computer saveComputerWithoutRollBack(Computer computer);
}
