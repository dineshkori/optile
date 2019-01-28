/**
 * 
 */
package com.optile.base.job;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author DineshKori
 *
 */
public abstract class Job implements IJobs, Comparable<Job>, Callable<String> {

	/**
	 * Will Define the Status of Job i.e QUEUED, RUNNING, SUCCESS, FAILED
	 */
	private String status;

	/**
	 * Human readable Name for the Job
	 */
	private String name;

	/**
	 * Starting Time for The Job in Date Format
	 */
	private Date timeStarted;

	/**
	 * Time When Job is expected to be started
	 */
	private Date scheduledStartTime;

	/**
	 * Will define the Priority of Job 1 to 5. priority 1 is Smallest to 5 is
	 * highest.
	 */
	private int priority;

	public Date getScheduledStartTime() {
		return scheduledStartTime;
	}

	public void setScheduledStartTime(Date scheduledStartTime) {
		this.scheduledStartTime = scheduledStartTime;
	}

	/**
	 * Time When job is Submitted to Queue
	 */
	private Date submitedTime;

	/**
	 * Time When job is completed
	 */
	private Date completedTime;

	/**
	 * Define if Job can be rollback or not.
	 */
	private boolean isJobRollable;

	/**
	 * Define if Job is CPU intensive operation or not. Scheduler will use while
	 * assigning job to executor.
	 */
	private boolean isCPUintensiveJob;

	private boolean isImmediateJob;

	public Date getTimeStarted() {
		return timeStarted;
	}

	public void setTimeStarted(Date timeStarted) {
		this.timeStarted = timeStarted;
	}

	public boolean isConfigureable() {
		return isConfigureable;
	}

	public void setConfigureable(boolean isConfigureable) {
		this.isConfigureable = isConfigureable;
	}

	private boolean isConfigureable;

	public boolean isImmediateJob() {
		return isImmediateJob;
	}

	public void setImmediateJob(boolean isImmediateJob) {
		this.isImmediateJob = isImmediateJob;
	}

	public boolean isCPUintensiveJob() {
		return isCPUintensiveJob;
	}

	public void setCPUintensiveJob(boolean isCPUintensiveJob) {
		this.isCPUintensiveJob = isCPUintensiveJob;
	}

	public boolean isJobRollable() {
		return isJobRollable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setJobRollable(boolean isJobRollable) {
		this.isJobRollable = isJobRollable;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getSubmitedTime() {
		return submitedTime;
	}

	public void setSubmitedTime(Date submitedTime) {
		this.submitedTime = submitedTime;
	}

	public Date getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(Date completedTime) {
		this.completedTime = completedTime;
	}

	public Job() {
	}

	public Job(String status, String name, Integer priority, Date startTime) {
		super();
		this.status = status;
		this.name = name;
		this.priority = priority;
		this.timeStarted = startTime;
	}

	public Date getStartTime() {
		return timeStarted;
	}

	public void setStartTime(Date startTime) {
		this.timeStarted = startTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Job name:  " + name + "" + "[status=" + status + ", priority=" + priority + ", startTime=" + timeStarted
				+ ", submitedTime=" + submitedTime + ", completedTime=" + completedTime + ", isJobRollable="
				+ isJobRollable + ", isCPUintensiveJob=" + isCPUintensiveJob + "]";
	}

	public int compareTo(Job other) {
		if (this.getPriority() > other.getPriority()) {
			return 1;
		} else if (this.getPriority() < other.getPriority()) {
			return -1;
		} else {
			return 0;
		}
	}

}
