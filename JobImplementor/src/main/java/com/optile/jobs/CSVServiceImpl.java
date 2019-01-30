package com.optile.jobs;

import com.optile.base.job.Job;
import com.optile.exception.JobExecutionException;
import com.optile.jobs.helper.CSVJobHelper;

/**
 * Jobs to Read CSV and perform some Operation
 * 
 * @author DineshKori
 *
 */
public class CSVServiceImpl extends Job {

	public String call() {
		return this.runnner();
	}

	public String runnner() {
		this.setStatus(RUNNING);
		try {
			for (int i = 0; i < 10000; i++) {
				if (i == 5000) {
					throw new JobExecutionException();
				}
			}
		} catch (JobExecutionException e) {
			System.err.println("CSVJobs runnner Method Exception");
			this.setStatus(FAILED);

		} finally {
			// TODO: handle finally clause
			if (this.getStatus().equalsIgnoreCase(FAILED) && this.isJobRollable()) {
				this.rollbackjob();
				return FAILED;
			}
		}
		return SUCCESS;
	}

	public void rollbackjob() {
		System.err.println("CSVJobs rollbackjob Method");
		this.setStatus(FAILED);
	}

	/**
	 * This is used to Set some configuration for this Job and should be using some
	 * properties file so that each Job could use specific configuration
	 * 
	 * @throws JobExecutionException
	 */
	public void initialJobContext() throws JobExecutionException {
		if (this.isConfigureable()) {
			// TODO Read JOB Specific initialization configuration from read from
			// configfileName and add to be job Definition
			CSVJobHelper helper = new CSVJobHelper();
			helper.initialJobContext(this.getConfigFile());
		}
	}

}
