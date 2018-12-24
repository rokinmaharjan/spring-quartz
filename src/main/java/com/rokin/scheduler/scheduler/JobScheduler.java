package com.rokin.scheduler.scheduler;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobScheduler {
	
	@Autowired
	private Scheduler scheduler;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JobScheduler.class);

	public void scheduleJob(SchedulerDto schedulerDto) throws SchedulerException {
		JobDetail job = JobBuilder.newJob(schedulerDto.getJobClass())
				.withIdentity(schedulerDto.getJobName(), schedulerDto.getJobGroup())
				.storeDurably()
				.build();
		
		try {
			this.scheduler.addJob(job, false);
		} catch (SchedulerException e) {
			LOGGER.info("Exception occured: {}", e);
			throw e;
		}
		
		Trigger trigger = TriggerBuilder.newTrigger().forJob(job)
				.withIdentity(schedulerDto.getTriggerName(), schedulerDto.getTriggerGroup())
			      .startNow()
			            .withSchedule(SchedulerUtils.buildCronScheduler(schedulerDto.getScheduleDetails()))
			      .build();
		
		this.scheduler.scheduleJob(trigger);
	}
	
	
	

}
