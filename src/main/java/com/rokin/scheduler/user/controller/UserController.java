package com.rokin.scheduler.user.controller;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rokin.scheduler.scheduler.SchedulerDto;
import com.rokin.scheduler.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/schedule")
	@ResponseStatus(HttpStatus.OK)
	public void scheduleUserJob(@RequestBody SchedulerDto schedulerDto) throws SchedulerException {
		this.userService.scheduleUserJob(schedulerDto);
	}

}
