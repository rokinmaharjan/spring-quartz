package com.rokin.scheduler.scheduler;

import org.quartz.Job;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SchedulerDto {
	private String jobName;
	@JsonIgnore
	private String jobGroup;
	private String triggerName;
	@JsonIgnore
	private String triggerGroup;
	@JsonIgnore
	private Class<? extends Job> jobClass;
	private ScheduleDetails scheduleDetails;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder(toBuilder = true)
	public static class ScheduleDetails {
		private int minutes;
		private int hour;
		private ScheduleType scheduleType;
		private int dayOfWeek; // Sunday is 1
		private int dayOfMonth;
		private Integer[] daysOfWeek;

	}

}
