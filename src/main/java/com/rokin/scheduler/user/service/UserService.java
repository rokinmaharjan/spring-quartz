package com.rokin.scheduler.user.service;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rokin.scheduler.scheduler.JobScheduler;
import com.rokin.scheduler.scheduler.SchedulerDto;
import com.rokin.scheduler.user.scheduler.UserJob;

@Service
public class UserService {
	
	@Autowired
	private JobScheduler jobScheduler;

	public void scheduleUserJob(SchedulerDto schedulerDto) throws SchedulerException {
		schedulerDto.toBuilder()
			.jobClass(UserJob.class)
			.jobGroup("users")
			.triggerGroup("users")
			.build();
		
		this.jobScheduler.scheduleJob(schedulerDto);
	}

}
