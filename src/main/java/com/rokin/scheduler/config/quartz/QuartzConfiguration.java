package com.rokin.scheduler.config.quartz;

import java.io.IOException;
import java.util.Properties;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfiguration {
	
	@Autowired
	private Environment environment;

	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("quartz/quartz-" + environment.getActiveProfiles()[0] + ".properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}

	@Bean
	public Scheduler getScheduler() throws Exception {
		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		// this allows to update triggers in DB when updating settings in config file:
		schedulerFactory.setOverwriteExistingJobs(true);
		schedulerFactory.setQuartzProperties(quartzProperties());
		schedulerFactory.afterPropertiesSet();

		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.start();

		return scheduler;
	}

}
