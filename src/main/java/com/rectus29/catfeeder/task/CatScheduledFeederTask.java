package com.rectus29.catfeeder.task;
/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 03/10/2018 11:34               */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

import com.rectus29.catfeeder.utils.SchedulingPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class CatScheduledFeederTask {

	private List<SchedulingPattern> schedulingPatterns = new ArrayList<>();
	private Class<? extends TimerTask> runnableTask;

	public CatScheduledFeederTask() {
	}

	public List<SchedulingPattern> getSchedulingPatterns() {
		return schedulingPatterns;
	}

	public Class<? extends Runnable> getRunnableTask() {
		return runnableTask;
	}

	public CatScheduledFeederTask setSchedulingPatterns(List<SchedulingPattern> schedulingPatterns) {
		this.schedulingPatterns = schedulingPatterns;
		return this;
	}

	public CatScheduledFeederTask setRunnableTask(Class<? extends TimerTask> runnableTask) {
		this.runnableTask = runnableTask;
		return this;
	}
}
