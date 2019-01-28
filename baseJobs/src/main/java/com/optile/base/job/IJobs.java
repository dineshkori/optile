/**
 * 
 */
package com.optile.base.job;

import org.springframework.transaction.annotation.Transactional;

import com.optile.exception.JobExecutionException;

/**
 * Defines method to be implemented by Job
 * 
 * @author DineshKori
 *
 */
public interface IJobs {

	public static final String QUEUED = "QUEUED";
	public static final String RUNNING = "RUNNING";
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED";

	/**
	 * This method should be Overridden by all the jobs for performing tasks.
	 * 
	 * @return Should return the Status of Job
	 * @throws Exception
	 */
	@Transactional
	public String runnner() throws JobExecutionException;

	/**
	 * This Method should be overridden by Job implementor so that it can be used
	 * for doing rollback operation such as deleting temporary, or files create etc
	 * 
	 * @return Status of the rollback Operation of the job.
	 * @throws JobExecutionException
	 */
	@Transactional
	public void rollbackjob() throws JobExecutionException;

}
