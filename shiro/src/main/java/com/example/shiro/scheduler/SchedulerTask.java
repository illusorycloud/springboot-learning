package com.example.shiro.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 简单定时任务
 *
 * @author illusory
 */
@Component
public class SchedulerTask {
    int count = 0;

    @Scheduled(cron = "*/6 * * * * ?")
    public void task1() {
        System.out.println("定时任务开启" + count++);
    }
}
