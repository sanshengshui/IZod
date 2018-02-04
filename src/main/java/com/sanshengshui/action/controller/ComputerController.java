package com.sanshengshui.action.controller;

import com.sanshengshui.action.entity.Computer;
import com.sanshengshui.action.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 穆书伟
 * @description 控制器类
 * @date 2018年2月4日 下午14:02
 */
@RestController
@RequestMapping("/computer")
public class ComputerController {
    @Autowired
    ComputerService computerService;

    @RequestMapping("/rollback")
    public Computer rollback(Computer computer){
        return computerService.saveComputerWithRollBack(computer);
    }

    @RequestMapping("/norollback")
    public Computer noRollback(Computer computer){
        return computerService.saveComputerWithoutRollBack(computer);
    }

}
