package com.demo;

import org.quartz.Trigger;
import static org.quartz.JobBuilder.*;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzMain {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws SchedulerException {
		// define a job class and tie it to our job class
		JobDetail job = JobBuilder.newJob(QuartzJob.class).build();

		Trigger t1 = TriggerBuilder.newTrigger().withIdentity("SimpleTrigger").startNow().build();
		Trigger t2 = TriggerBuilder.newTrigger().withIdentity("cronJob").withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")).build();
		Trigger t3 = TriggerBuilder.newTrigger().withIdentity("CronTrigger")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(05).repeatForever()).build();
		Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
		sc.start();
//		sc.scheduleJob(job, t1);
//		sc.scheduleJob(job, t2);
		sc.scheduleJob(job, t3);

	}
}
