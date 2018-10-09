package com.rectus29.catfeeder.scheduler;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 03/10/2018 14:35               */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

import com.rectus29.catfeeder.task.CatScheduledFeederTask;
import com.rectus29.catfeeder.task.DummyTask;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CatFeederScheduler {

	private Logger logger = Logger.getLogger(CatFeederScheduler.class);
	private ScheduledExecutorService scheduler;
	private List<CatScheduledFeederTask> feederTaskList = new ArrayList<>();
	private List<ScheduledFuture> scheduledFutures = new ArrayList<>();


	public CatFeederScheduler() {
		this.scheduler = Executors.newScheduledThreadPool(4);
	}

	public void schedule(CatScheduledFeederTask catFeedTask){
		try {
			logger.debug("scheduled new task");
			scheduledFutures.add(this.scheduler.scheduleAtFixedRate(catFeedTask.getRunnableTask(), 0, 1, TimeUnit.MINUTES));
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
	}

	public void unSchedule(CatScheduledFeederTask catFeedTask){

	}

	public List<CatScheduledFeederTask> getFeederTaskList() {
		return feederTaskList;
	}

	public void unScheduleAll() {
		logger.debug("clear all scheduled task");
		this.scheduler.shutdown();
	}
}
