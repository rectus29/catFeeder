package com.rectus29.catfeeder;

import com.google.gson.*;
import com.rectus29.catfeeder.enums.ApplicationState;
import com.rectus29.catfeeder.exception.CatFeederException;
import com.rectus29.catfeeder.scheduler.CatFeederScheduler;
import com.rectus29.catfeeder.serializer.CatFeedTaskSerializer;
import com.rectus29.catfeeder.serializer.CatFeederConfigurationSerializer;
import com.rectus29.catfeeder.serializer.SchedulingPatternSerializer;
import com.rectus29.catfeeder.task.CatFeedTask;
import com.rectus29.catfeeder.utils.SchedulingPattern;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

import java.io.File;

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

	private static final Logger logger = LogManager.getLogger(CatFeederApplication.class);
	private static CatFeederApplication ourInstance = new CatFeederApplication();
	private ApplicationState applicationState = ApplicationState.NOTSTARTED;
	private CatFeederScheduler catFeederScheduler;
	private CatFeederConfiguration catFeederConfiguration;
	private String configFilePath = "catFeederConfiguration.json";
	private GsonBuilder gsonBuilder =  new GsonBuilder()
			.registerTypeAdapter(CatFeederConfiguration.class, new CatFeederConfigurationSerializer())
			.registerTypeAdapter(CatFeedTask.class, new CatFeedTaskSerializer())
			.registerTypeAdapter(SchedulingPattern.class, new SchedulingPatternSerializer());

	private CatFeederApplication() {
	}

	public static CatFeederApplication getInstance() {
		return ourInstance;
	}

	public void initApplication() {
		if (!this.applicationState.equals(ApplicationState.RUNNING)) {
			logger.info("Init CatFeederApplication");
			this.applicationState = ApplicationState.STARTING;
			//loading configuration File
			this.catFeederConfiguration = initConfig(this.configFilePath);
			//init scheduler
			this.catFeederScheduler = new CatFeederScheduler();
			//retreive task to schedule
			//TODO for dev manual building
			for(CatFeedTask temp : catFeederConfiguration.getScheduledTask()){
				catFeederScheduler.schedule(temp);
			}
			//end of the config
			this.applicationState = ApplicationState.RUNNING;
			logger.info("CatFeederApplication Started");
		} else {
			logger.info("Aplication already Start - ignoring command");
		}
	}

	private CatFeederConfiguration initConfig(String configFilePath) {
		try {
			File configFile = new File(getClass().getClassLoader().getResource(configFilePath).getFile());
			String configRaw = FileUtils.readFileToString(configFile);
			CatFeederConfiguration config = gsonBuilder.create().fromJson(configRaw, CatFeederConfiguration.class);
			if(config == null){
				throw new CatFeederException("unable to parse config");
			}
			return config;
		} catch (Exception e) {
			logger.error("Error while loading config file set default config", e);
		}
		return new CatFeederConfiguration();
	}

	public CatFeederScheduler getCatFeederScheduler() {
		return catFeederScheduler;
	}


	/**
	 * Return the current Server state as JSON
	 *
	 * @return jsonObject
	 */
	public JsonObject printState() {
		JsonObject out = this.gsonBuilder.create().toJsonTree(this.catFeederConfiguration).getAsJsonObject();
		out.addProperty("ServerState", this.applicationState.toString());
		out.addProperty("date", new DateTime().getMillis());
		return out;
	}

	public void applyNewConfiguration(JsonElement jsonConfig) throws Exception{
		CatFeederConfiguration newConf = this.gsonBuilder.create().fromJson(jsonConfig, CatFeederConfiguration.class);
		if(newConf != null) {
			//set the new Configuration
			this.catFeederConfiguration = newConf;
			//clear the scheduler and reset the new schedule
//		this.catFeederScheduler.unScheduleAll();
			//retreive task to schedule
			//TODO for dev manual building
//		CatScheduledFeederTask cstf = new CatScheduledFeederTask()
//				.setRunnableTask(DummyTask.class)
//				.setSchedulingPatterns(this.catFeederConfiguration.getScheduledTask());
//		catFeederScheduler.schedule(cstf);
		}
	}

	public CatFeederConfiguration saveConfiguration() {
		try {
			File configFile = new File(getClass().getClassLoader().getResource(configFilePath).getFile());
			FileUtils.writeStringToFile(configFile, gsonBuilder.create().toJson(this.catFeederConfiguration, CatFeederConfiguration.class));
		} catch (Exception e) {
			logger.error("Error while writing config to file", e);
		}
		return this.catFeederConfiguration;
	}
}
