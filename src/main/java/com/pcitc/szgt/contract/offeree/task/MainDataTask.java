package com.pcitc.szgt.contract.offeree.task;

import com.pcitc.szgt.contract.offeree.service.MainDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class MainDataTask {

    @Autowired
    private MainDataService mainDataService;

    // 每小时执行
//    @Scheduled(cron = "0 0 0/1 * * ?")
    // 每分钟执行
    @Scheduled(cron = "0 */10 * * * ?")
//    @Scheduled(cron = "0 0 1 ? * *")
    public void run() throws InterruptedException {
        mainDataService.fetchMainData();
    }

//    @Scheduled(cron = "0 0 0/1 * * ?")
    @Scheduled(cron = "0 */10 * * * ?")
    public void run2() throws InterruptedException{
        mainDataService.fetchInMainData();
    }

}
