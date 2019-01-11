package com.rokin.scheduler.user.service;

import java.util.Date;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rokin.scheduler.scheduler.JobScheduler;
import com.rokin.scheduler.scheduler.SchedulerDto;
import com.rokin.scheduler.scheduler.SchedulerDto.ScheduleDetails;
import com.rokin.scheduler.scheduler.SchedulerUtils;
import com.rokin.scheduler.user.scheduler.UserJob;

@Service
public class UserService {
	
	@Autowired
	private JobScheduler jobScheduler;
	
	@Autowired
	private Scheduler scheduler;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	private static final String JOB_GROUP = "USER-SCHEDULER";
	private static final String TRIGGER_GROUP = "USER-SCHEDULER";

	public void scheduleUserJob(ScheduleDetails scheduleDetails) throws SchedulerException {
		String jobName = "user-" + new Date().toString();
		String triggerName = "user-" + new Date().toString();
		
		try {
			if (SchedulerUtils.jobExists(scheduler, jobName, JOB_GROUP)) {
				throw new IllegalArgumentException(String.format("Job with name %s already exists", jobName));
			}
		} catch (SchedulerException e) {
			throw new IllegalArgumentException(e.getMessage());
		}		
		
		SchedulerDto schedulerDto = SchedulerDto.builder()
				.jobName(jobName)
				.jobGroup(JOB_GROUP)
				.triggerName(triggerName)
				.triggerGroup(TRIGGER_GROUP)
				.scheduleDetails(scheduleDetails)
				.jobClass(UserJob.class)
				.build();
		
		LOGGER.info("Creating user scheduler with schedule details: {}", schedulerDto);
		this.jobScheduler.scheduleJob(schedulerDto);
	}

}
