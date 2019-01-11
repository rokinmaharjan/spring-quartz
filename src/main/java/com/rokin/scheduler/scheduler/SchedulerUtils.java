package com.rokin.scheduler.scheduler;

import java.util.TimeZone;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;

import com.rokin.scheduler.scheduler.SchedulerDto.ScheduleDetails;

public class SchedulerUtils {
	
	private SchedulerUtils() {
		
	}
	
	public static CronScheduleBuilder buildCronScheduler(ScheduleDetails scheduleDetails) {
		CronScheduleBuilder cronScheduler;
		switch (scheduleDetails.getScheduleType()) {
			case MONTHLY:
				cronScheduler = CronScheduleBuilder
						.monthlyOnDayAndHourAndMinute(
								scheduleDetails.getDayOfMonth(), 
								scheduleDetails.getHour(),
								scheduleDetails.getMinutes()
								);
				break;

			case WEEKLY:
				cronScheduler = CronScheduleBuilder
						.atHourAndMinuteOnGivenDaysOfWeek(
								scheduleDetails.getHour(), 
								scheduleDetails.getMinutes(), 
								scheduleDetails.getDaysOfWeek()
								);
				break;
				
			default:
				cronScheduler = CronScheduleBuilder
						.dailyAtHourAndMinute(
								scheduleDetails.getHour(), 
								scheduleDetails.getMinutes()
								);
		}
		
		cronScheduler.inTimeZone(TimeZone.getTimeZone(scheduleDetails.getTimezone()));

		return cronScheduler;
	}
	
	public static Boolean triggerExists(Scheduler scheduler, String triggerName, String triggerGroup) throws SchedulerException {	
		return scheduler.checkExists(new TriggerKey(triggerName, triggerGroup));
	}
	
	public static Boolean jobExists(Scheduler scheduler, String jobName, String jobGroup) throws SchedulerException {
		return scheduler.checkExists(new JobKey(jobName, jobGroup));
	}
	
	public static Boolean deleteJob(Scheduler scheduler, String jobName, String jobGroup) throws SchedulerException {
		return scheduler.deleteJob(new JobKey(jobName, jobGroup));
	}
}
