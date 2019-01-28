/**
 * 
 */
package com.manager.job.rest;

import java.util.Map;

import com.optile.base.job.Job;

/**
 * @author DineshKori
 *
 */
public class JobInfoResponse {
	private Integer noOfjobs;
	private String status;
	Map<String, Job> jobs;

	public Integer getNoOfjobs() {
		return noOfjobs;
	}

	public void setNoOfjobs(Integer noOfjobs) {
		this.noOfjobs = noOfjobs;
	}


	public Map<String, Job> getJobs() {
		return jobs;
	}

	public void setJobs(Map<String, Job> jobs) {
		this.jobs = jobs;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
