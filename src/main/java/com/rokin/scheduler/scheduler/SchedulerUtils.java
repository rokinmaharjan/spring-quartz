package com.rokin.scheduler.scheduler;

import org.quartz.CronScheduleBuilder;

import com.rokin.scheduler.scheduler.SchedulerDto.ScheduleDetails;

public class SchedulerUtils {
	
	private SchedulerUtils() {
		
	}
	
	public static CronScheduleBuilder buildCronScheduler(ScheduleDetails scheduleDetails) {
		switch (scheduleDetails.getScheduleType()) {
			case WEEKLY:
				return CronScheduleBuilder
						.weeklyOnDayAndHourAndMinute(
								scheduleDetails.getDayOfWeek(), //Sunday is 1
								scheduleDetails.getHour(), 
								scheduleDetails.getMinutes()
								);
				
			case MONTHLY:
				return CronScheduleBuilder
						.monthlyOnDayAndHourAndMinute(
								scheduleDetails.getDayOfMonth(), 
								scheduleDetails.getHour(),
								scheduleDetails.getMinutes()
								);

			case DAYS_OF_WEEK:
				return CronScheduleBuilder
						.atHourAndMinuteOnGivenDaysOfWeek(
								scheduleDetails.getHour(), 
								scheduleDetails.getMinutes(), 
								scheduleDetails.getDaysOfWeek()
								);
			
			default:
				return CronScheduleBuilder
						.dailyAtHourAndMinute(
								scheduleDetails.getHour(), 
								scheduleDetails.getMinutes()
								);
		}

	}
}
