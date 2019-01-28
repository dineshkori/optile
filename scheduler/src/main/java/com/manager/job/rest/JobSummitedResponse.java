/**
 * 
 */
package com.manager.job.rest;

/**
 * @author DineshKori
 *
 */
public class JobSummitedResponse {

	private String status;
	private String msg;

	public String getMsg() {
		return msg;
	}

	@Override
	public String toString() {
		return "JobSummitedResponse [status=" + status + ", msg=" + msg + "]";
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
