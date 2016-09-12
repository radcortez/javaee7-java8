package com.radcortez.javaee.java8.timer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import java.io.Serializable;

/**
 * @author Roberto Cortez
 */
@Startup
@Singleton
public class TimerBean {
    @Resource
    private TimerService timerService;

    @PostConstruct
    public void init() {
        timerService.createCalendarTimer(
                new ScheduleExpression().hour("*").minute("*").second("*/5"),
                new TimerConfig((Serializable & Runnable) this::wakeUp, false));

        timerService.createCalendarTimer(
                new ScheduleExpression().hour("*").minute("*").second("*/5"),
                new TimerConfig((Serializable & Runnable) this::letMeSleep, false));
    }

    @Timeout
    public void timeout(final Timer timer) {
        ((Runnable) timer.getInfo()).run();
    }

    private void wakeUp() {
        System.out.println("Wake Up!");
    }

    private void letMeSleep() {
        System.out.println("Let me sleep!");
    }
}
