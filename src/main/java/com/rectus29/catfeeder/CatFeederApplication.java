package com.rectus29.catfeeder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rectus29.catfeeder.enums.ApplicationState;
import com.rectus29.catfeeder.scheduler.CatFeederScheduler;
import com.rectus29.catfeeder.task.CatFeedTask;
import com.rectus29.catfeeder.task.CatScheduledFeederTask;
import com.rectus29.catfeeder.utils.Mp3Player;
import com.rectus29.catfeeder.utils.SchedulingPattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 03/10/2018 10:35               */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

public class CatFeederApplication {

	private static final Logger logger= LogManager.getLogger(CatFeederApplication.class);
	private static CatFeederApplication ourInstance = new CatFeederApplication();
	private ApplicationState applicationState = ApplicationState.NOTSTARTED;
	private CatFeederScheduler catFeederScheduler;

	public static CatFeederApplication getInstance() {
		return ourInstance;
	}

	private CatFeederApplication() {
	}

	public void initApplication() {
		if(!this.applicationState.equals(ApplicationState.RUNNING)) {
			logger.info("Init CatFeederApplication");
			this.applicationState = ApplicationState.STARTING;
			//init scheduler
			this.catFeederScheduler = new CatFeederScheduler();
			//retreive task to schedule
			//TODO for dev manual building
			CatScheduledFeederTask cstf = new CatScheduledFeederTask()
					.setRunnableTask(CatFeedTask.class)
					.setSchedulingPatterns(SchedulingPattern.buildFromPatternString("*,08:30,00:00|*,18:30,00:00"));

			catFeederScheduler.schedule(cstf);

			//send notif

			this.applicationState = ApplicationState.RUNNING;
			logger.info("CatFeederApplication Started");
		}else{
			logger.info("Aplication already Start - ignoring command");
		}
	}

	public CatFeederScheduler getCatFeederScheduler() {
		return catFeederScheduler;
	}

	public CatFeederApplication scheduleThis(String json){
		new Mp3Player().play("plop.mp3");
		return this;
	}


	/**
	 * Return the current Server state as JSON
	 * @return jsonObject
	 */
	public JsonObject printState(){
		JsonObject out = new JsonObject();
		out.addProperty("ServerState", this.applicationState.toString());
		out.addProperty("date" , new DateTime().getMillis());
		JsonArray taskArray = new JsonArray();
		Gson gson = new Gson()
		for(CatScheduledFeederTask temp : this.getCatFeederScheduler().getFeederTaskList()){
			taskArray.add(gson.toJson(temp));
		}
		out.add("schedule", taskArray);
		return out;
	}
}
