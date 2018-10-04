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

import com.rectus29.catfeeder.task.CatFeedTask;
import com.rectus29.catfeeder.task.CatScheduledFeederTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class CatFeederScheduler {

	private ScheduledExecutorService scheduler;
	private List<CatScheduledFeederTask> feederTaskList = new ArrayList<>();


	public CatFeederScheduler() {
		this.scheduler = Executors.newScheduledThreadPool(4);
	}

	public void schedule(CatFeedTask catFeedTask){

	}

	public void unSchedule(CatFeedTask catFeedTask){

	}

	public List getSchedulList(){
		List out = new ArrayList();
		return out;
	}
}
