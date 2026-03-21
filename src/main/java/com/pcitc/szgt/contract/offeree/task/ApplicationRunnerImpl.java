package com.pcitc.szgt.contract.offeree.task;

import com.pcitc.szgt.contract.offeree.service.MainDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 启动后立即请求mdm数据
 */
//@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Autowired
    private MainDataService mainDataService;

    @Override
    public void run(ApplicationArguments args) {
        try {
            mainDataService.fetchMainData();
        }catch (Exception e){

        }

    }
}
