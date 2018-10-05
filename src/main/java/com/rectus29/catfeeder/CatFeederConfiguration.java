package com.rectus29.catfeeder;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 05/10/2018 11:18                */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.rectus29.catfeeder.scheduler.CatFeederScheduler;
import com.rectus29.catfeeder.serializer.CatFeederConfigurationSerializer;
import com.rectus29.catfeeder.serializer.SchedulingPatternSerializer;
import com.rectus29.catfeeder.task.CatScheduledFeederTask;
import com.rectus29.catfeeder.utils.SchedulingPattern;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CatFeederConfiguration {
	private static Logger logger = LogManager.getLogger(CatFeederScheduler.class);
	private String version;
	private String buildNumber;
	private int openingTime;
	private List<SchedulingPattern> scheduledTask = new ArrayList<>();

	public CatFeederConfiguration() {
	}

	public String getVersion() {
		return version;
	}

	public CatFeederConfiguration setVersion(String version) {
		this.version = version;
		return this;
	}

	public String getBuildNumber() {
		return buildNumber;
	}

	public CatFeederConfiguration setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
		return this;
	}

	public int getOpeningTime() {
		return openingTime;
	}

	public CatFeederConfiguration setOpeningTime(int openingTime) {
		this.openingTime = openingTime;
		return this;
	}

	public List<SchedulingPattern> getScheduledTask() {
		return scheduledTask;
	}

	public CatFeederConfiguration setScheduledTask(List<SchedulingPattern> scheduledTask) {
		this.scheduledTask = scheduledTask;
		return this;
	}
}
