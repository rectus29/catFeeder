package com.rectus29.catfeeder.scheduler;

/*-----------------------------------------------------*/
/*						rectus29					   */
/*                                                     */
/*                Date: 03/10/2018 14:35               */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

import com.rectus29.catfeeder.task.CatFeedTask;
import com.rectus29.catfeeder.utils.SchedulingPattern;
import org.apache.log4j.Logger;

import java.util.*;

public class CatFeederScheduler {

	private static final String TIMERNAME = "catFeederTimer";
	private Logger logger = Logger.getLogger(CatFeederScheduler.class);
	private Timer schedulerTimer;
	private List<CatFeedTask> feederTaskList = new ArrayList<>();


	public CatFeederScheduler() {
		this.schedulerTimer = new Timer(TIMERNAME, true);
	}

	public void schedule(CatFeedTask catFeedTask) {
		logger.debug("scheduled new task");
		feederTaskList.add(catFeedTask);
		//compute next executionDate
		for (SchedulingPattern tempPattern : catFeedTask.getSchedulingPatterns()) {
			Date nextDate = tempPattern.getNextExecutionDate(new Date());
			schedulerTimer.scheduleAtFixedRate(catFeedTask, nextDate, 24 * 60 * 60 * 1000);
		}
	}

	public void unSchedule(CatFeedTask catFeedTask) {
		for (CatFeedTask temp : this.feederTaskList) {
			if (Objects.equals(temp, catFeedTask)) {
				temp.cancel();
			}
		}
		this.feederTaskList.remove(catFeedTask);
	}

	public List<CatFeedTask> getFeederTaskList() {
		return feederTaskList;
	}

	public void unScheduleAll() {
		logger.debug("clear all scheduled task");
		this.schedulerTimer.cancel();
	}
}
