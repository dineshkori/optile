package com.manager.job.service;

import java.util.Map;
import java.util.concurrent.Future;

import com.optile.base.job.Job;

/**
 * @author DineshKori
 *
 */
public interface JobManagerService {

	/**
	 * @param jobInfo
	 */
	public void sumitJob(Job jobInfo);

	/**
	 * get the status of Jobs in Queue.
	 * 
	 * @return Status of the jobs
	 */
	public Map<String, Job> getAllStatus();

	/**
	 * For Job to be started immediately
	 * 
	 * @param jobInfo
	 */
	public void sumitJobImmediately(Job jobInfo);


	/**
	 * Will return The Jobs of given Status
	 * 
	 * @return
	 */
	public Map<String, Future<String>> getJobsByStatus(String Status);

}