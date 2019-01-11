package com.rokin.scheduler.user.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class UserJob implements Job {
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.err.println("User Job is Running");
	}

}
