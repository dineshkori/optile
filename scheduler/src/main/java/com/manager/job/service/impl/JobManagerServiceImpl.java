package com.manager.job.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.manager.job.service.JobManagerService;
import com.optile.base.job.Job;

@Service
public class JobManagerServiceImpl implements JobManagerService {

	public static final Logger logger = LoggerFactory.getLogger(JobManagerServiceImpl.class);

	@Autowired
	Environment env;

	/**
	 * Keep The Status of All Submitted Jobs
	 */
	Map<String, Future<String>> summitedJobStatus = new ConcurrentHashMap<String, Future<String>>();

	Map<String, String> jobStatus = new HashMap<String, String>();

	Map<String, Job> jobsInfo = new ConcurrentHashMap<String, Job>();

	/**
	 * Contains all Jobs which are scheduled for Current System Date.
	 */
	PriorityBlockingQueue<Job> todayJobQueue = new PriorityBlockingQueue<Job>();

	/**
	 * Jobs in this Queue needs to persisted or put in Some JMS as they need to be
	 * reloaded once system is restarted
	 */
	PriorityBlockingQueue<Job> futureScheduledQueue = new PriorityBlockingQueue<Job>();

	@Override
	@Async
	public void sumitJob(Job job) {
		logger.info("Job Queued ... " + job.getName() + " at " + System.currentTimeMillis());
		loadTodaysJobFromDB();

		if (job.isImmediateJob()) {
			summitedJobStatus.put(job.getName(), immediateJobExecutor.submit(job));
		}

		job.setScheduledStartTime(new Date());
		todayJobQueue.add(job);
		if (!todayJobQueue.isEmpty()) {
			executeJob();
		}
		job.setStatus(Job.QUEUED);
		if (!jobStatus.containsKey(job.getName())) {
			jobStatus.put(job.getName(), Job.QUEUED);
		}

		if (!jobsInfo.containsKey(job.getName())) {
			jobsInfo.put(job.getName(), job);
		}
	}

	/**
	 * This will load all SystemDate Jobs From DB or from Persistent layer
	 * and Add to todays priority Queue
	 */
	private void loadTodaysJobFromDB() {
		// TODO Auto-generated method stub

	}

	/**
	 * This will start the Jobs Immediately, will not use Queue for execution of
	 * Such Jobs
	 */
	private ExecutorService immediateJobExecutor = Executors.newFixedThreadPool(5);

	private ExecutorService sequenceExecutor = Executors.newFixedThreadPool(2);

	private static int maxJobs = 5;

	private static int sumittedJobs = 0;

	private Thread jobPollingThread = new Thread(() -> {
		while (true) {
			try {
				Job job = todayJobQueue.take();
				sumittedJobs++;
				if (job != null) {
					if (sumittedJobs != maxJobs) {
						summitedJobStatus.put(job.getName(), sequenceExecutor.submit(job));
					} else {
						sumittedJobs = 0;
						logger.info("Waiting for Submitted jobs to complete");
						Thread.sleep(80000);
					}
				}

			} catch (InterruptedException e) {
				logger.error("Error " + e.toString());
			} finally {
				if (!sequenceExecutor.isTerminated()) {
					logger.warn("cancel non-finished tasks");
				}
			}
		}
	});

	@Scheduled(cron = "${cone.value}")
	private void executeJob() {
		logger.info(
				"Checking Today's Job Pool Size " + todayJobQueue.size() + " at time " + System.currentTimeMillis());
		if (!jobPollingThread.isAlive()) {
			jobPollingThread.start();
		}
	}

	@Override
	public void sumitJobImmediately(Job job) {
		logger.info("immediate job");
		job.setPriority(5);
		job.setScheduledStartTime(new Date(System.currentTimeMillis()));
		job.setJobRollable(true);
		todayJobQueue.add(job);
	}

	@Override
	public Map<String, Job> getAllStatus() {
		for (Map.Entry<String, Future<String>> job : this.summitedJobStatus.entrySet()) {
			try {
				if (jobsInfo.containsKey(job.getKey())) {
					Job tempjob = jobsInfo.get(job.getKey());
					tempjob.setStatus(job.getValue().get(100, TimeUnit.MILLISECONDS));
					jobsInfo.put(job.getKey(), tempjob);
				}
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return jobsInfo;
	}

}