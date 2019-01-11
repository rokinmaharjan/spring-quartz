package com.rokin.scheduler.scheduler;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
		@NotNull
		private Integer minutes;
		@NotNull
		private Integer hour;
		@NotNull
		private ScheduleType scheduleType;
		private Integer dayOfWeek; // Sunday is 1
		private Integer dayOfMonth;
		private Integer[] daysOfWeek;
		@NotEmpty
		private String timezone;
	}

}
