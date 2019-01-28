package com.manager.job.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.manager.job.rest.JobInfoResponse;
import com.manager.job.rest.JobSummitedResponse;
import com.manager.job.service.JobManagerService;
import com.manager.job.service.impl.Util;
import com.optile.base.job.Job;

@RestController

public class JobManagerRestController {

	public static final Logger logger = LoggerFactory.getLogger(JobManagerRestController.class);

	@Autowired
	Util util;
	
	@Autowired
	JobManagerService jobManager;

	@RequestMapping(value = "/jobs/api/getAllJobs", method = RequestMethod.GET, produces = "application/json")
	public JobInfoResponse getAlljobStatus() {
		JobInfoResponse response = new JobInfoResponse();
		response.setJobs(jobManager.getAllStatus());
		response.setNoOfjobs(jobManager.getAllStatus().size());
		response.setStatus("SUCESS");
		return response;
	}

	/*
	 * @RequestMapping(value = "/jobs/api/sumitjob", method = RequestMethod.POST,
	 * produces = "application/json", consumes = "application/json") public
	 * JobSummitedResponse sumitJob() { JobSummitedResponse response = new
	 * JobSummitedResponse(); Job job = new CSVServiceImpl();
	 * job.setName("CSVServiceImpl"); jobManager.sumitJob(job);
	 * response.setStatus("SUCESS"); return response; }
	 */

	@RequestMapping(value = "/jobs/api/job", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public JobSummitedResponse sumitJobofType(String type, int jobpriority) {
		JobSummitedResponse response = new JobSummitedResponse();
		Job job = util.getInstance(type);
		job.setName(type + "@" + System.currentTimeMillis());
		job.setPriority(jobpriority);
		jobManager.sumitJob(job);
		response.setStatus("SUCESS");
		return response;
	}

}
