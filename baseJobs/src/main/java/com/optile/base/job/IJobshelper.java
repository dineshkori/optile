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
	 * Can do some initialization for task like file location, Emailer list, Emailer
	 * message etc This is used to set some configuration for this Job and should be
	 * using some properties file so that each Job could use specific configuration
	 * 
	 * @throws JobExecutionException
	 */
	public void initialJobContext(String configFileName) throws JobExecutionException;

}
