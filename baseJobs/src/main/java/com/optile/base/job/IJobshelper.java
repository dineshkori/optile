/**
 * 
 */
package com.optile.base.job;

import com.optile.exception.JobExecutionException;

/**
 * Defines if Job need to initialize some context before it can be scheduled for
 * Execution
 * 
 * @author DineshKori
 *
 */
public interface IJobshelper {

	/**
	 * Can Do some initialization for task like file location, Emailer list, Emailer
	 * message etc
	 * 
	 * @throws JobExecutionException
	 */
	public void initialJobContext() throws JobExecutionException;

}
