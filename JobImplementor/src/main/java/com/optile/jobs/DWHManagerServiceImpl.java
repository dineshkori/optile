package com.optile.jobs;

import com.optile.base.job.Job;
import com.optile.exception.JobExecutionException;

/**
 * @author DineshKori
 *
 */
public class DWHManagerServiceImpl extends Job {

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
			System.err.println("DWHJob runnner Method Exception");
			this.setStatus(FAILED);

		} finally {
			// TODO: handle finally clause
			if (this.getStatus().equalsIgnoreCase(FAILED) && this.isJobRollable()) {
				this.rollbackjob();
			}
		}
		return SUCCESS;
	}

	public void rollbackjob() {
		System.err.println("DWHJob rollbackjob Method");
		this.setStatus(FAILED);
	}

	/**
	 * @throws JobExecutionException
	 */
	public void initialJobContext() throws JobExecutionException {
		// TODO Auto-generated method stub

	}

}