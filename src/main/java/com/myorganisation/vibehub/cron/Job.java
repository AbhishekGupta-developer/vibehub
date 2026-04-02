package com.myorganisation.vibehub.cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Job {

    // Expression meaning
    // * -> Every value
    // ? -> No specific value
    // , -> Multiple values
    // / -> Interval
    // - -> Range

    // every second
//    @Scheduled(cron = "* * * * * ?")
//    public void printHello() {
//        System.out.println("Hello");
//    }

    // every minute
//    @Scheduled(cron = "0 * * * * ?")

    // two times every minute
//    @Scheduled(cron = "0,30 * * * * ?")

    // five times every minute
//    @Scheduled(cron = "*/5 * * * * ?")

//    @Scheduled(cron = "0-5 * * * * ?")

//    @Scheduled(cron = "0 */5 0-7,17-23 * * SAT,SUN")

    // Except 1st Jan
    @Scheduled(cron = "0 0 1 2-31 JAN ?")
    @Scheduled(cron = "0 0 1 * FEB-DEC ?")
    public void printHello() {
        System.out.println("Hello");
    }
}















