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

import com.rectus29.catfeeder.task.CatFeedTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CatFeederConfiguration {
	private String version = "Default-Version";
	private String buildNumber = "0000";
	private Date updateDate = new Date();
	private long openingTime = new Date().getTime();
	private List<CatFeedTask> feederScheduleEntries = new ArrayList<>();

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

	public long getOpeningTime() {
		return openingTime;
	}

	public CatFeederConfiguration setOpeningTime(int openingTime) {
		this.openingTime = openingTime;
		return this;
	}

	public List<CatFeedTask> getScheduledTask() {
		return feederScheduleEntries;
	}

	public CatFeederConfiguration setScheduledTask(List<CatFeedTask> scheduledTask) {
		this.feederScheduleEntries = scheduledTask;
		return this;
	}



	public Date getUpdateDate() {
		return updateDate;
	}

	public CatFeederConfiguration setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
		return this;
	}
}
