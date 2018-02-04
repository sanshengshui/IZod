package com.sanshengshui.action.service.impl;

import com.sanshengshui.action.dao.ComputerRepository;
import com.sanshengshui.action.entity.Computer;
import com.sanshengshui.action.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ComputerServiceImpl implements ComputerService {
    @Autowired
    ComputerRepository computerRepository;

    @Override
    @Transactional(rollbackOn = {IllegalArgumentException.class})
    public Computer saveComputerWithRollBack(Computer computer)  {
        Computer com = computerRepository.save(computer);
        if (computer.getName().equals("汪云飞")){
            try {
                throw new IllegalAccessException("汪云飞已存在，数据将回滚");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return com;
    }

    @Override
    @Transactional(dontRollbackOn = {IllegalArgumentException.class})
    public Computer saveComputerWithoutRollBack(Computer computer) {
        Computer com = computerRepository.save(computer);
        if (computer.getName().equals("汪云飞")){
            throw new IllegalArgumentException("汪云飞虽已存在，数据将不会回滚");
        }
        return com;
    }
}
