/**
 * 
 */
package com.manager.job.service.impl;

import org.springframework.stereotype.Service;

import com.optile.base.job.Job;
import com.optile.jobs.CSVServiceImpl;
import com.optile.jobs.DWHManagerServiceImpl;
import com.optile.jobs.EmailManagerServiceImpl;
import com.optile.jobs.FileReaderManagerServiceImpl;

/**
 * @author DineshKori
 *
 */
@Service
public class Util {

	public Job getInstance(String jobName) {
		Job job = null;
		if (jobName.equalsIgnoreCase("EMAIL")) {
			job = new EmailManagerServiceImpl();
		} else if (jobName.equalsIgnoreCase("CSV")) {
			job = new CSVServiceImpl();
		} else if (jobName.equalsIgnoreCase("FILE")) {
			job = new FileReaderManagerServiceImpl();
		} else if (jobName.equalsIgnoreCase("DWH")) {
			job = new DWHManagerServiceImpl();
		}
		return job;
	}

}
