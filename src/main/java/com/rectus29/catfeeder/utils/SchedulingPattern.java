package com.rectus29.catfeeder.utils;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 03/10/2018 11:01               */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Class keep data of a week frequency from a pattern String
 * ex [dayNumber],[Start hh:mm],[End HH:mm]|[dayNumber],[Start hh:mm],[End HH:mm]...
 *
 * day are numbered as DateTimeConstants
 * hour are in 24 hour format
 * @see org.joda.time.DateTimeConstants
 * @author Rectus29
 */

public class SchedulingPattern {

	private static Logger logger = LogManager.getLogger(SchedulingPattern.class);
	private String uid = UUID.randomUUID().toString();
	private int dayNumber;
	private LocalTime startTime;
	private LocalTime endTime;

	/**
	 * Build Frequency pattern object from string,
	 *
	 * note: if a pattern cannot be compute it will be ommitted
	 *
	 * @param patternString a correct formatted pattern string
	 * @return list of Frequency pattern Object
	 */
	public static List<SchedulingPattern> buildFromPatternString(String patternString) {
		List<SchedulingPattern> out = new ArrayList<>();
		if (StringUtils.isEmpty(patternString)) {
			return out;
		}
		//build pattern from mulptile pattren string
		for (String dailyPattern : patternString.split("\\|")) {
			try {
				SchedulingPattern fp = new SchedulingPattern();
				String dayNumber = dailyPattern.split(",")[0];
				String dailyStartTime = dailyPattern.split(",")[1];
				String dailyEndTime = dailyPattern.split(",")[2];
				//build a frequency pattern
				//if daynumber set to * put 0 to mark everyDay
				fp.setDayNumber(("*".equals(dayNumber))? 0 : Integer.parseInt(dayNumber));
				fp.setStartTime(new LocalTime(
						Integer.parseInt(dailyStartTime.split(":")[0]),
						Integer.parseInt(dailyStartTime.split(":")[1]))
				);
				fp.setEndTime(new LocalTime(
						Integer.parseInt(dailyEndTime.split(":")[0]),
						Integer.parseInt(dailyEndTime.split(":")[1]))
				);
				//add to returned collections
				out.add(fp);
			} catch (Exception e) {
				logger.error("Error while building pattern from string :\" " + dailyPattern + "\"", e);
			}
		}
		return out;
	}

	public int getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(int dayNumber) {
		this.dayNumber = dayNumber;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getUid() {
		return uid;
	}

	public SchedulingPattern setUid(String uid) {
		this.uid = uid;
		return this;
	}

	@Override
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormat.forPattern("HH:mm");
		return dayNumber +
				"," + dtf.print(startTime) +
				"," + dtf.print(endTime);

	}

	public Date getNextExecutionDate(Date date) {
		DateTime dt = new DateTime(date);
		dt = dt.withDayOfWeek(this.getDayNumber());
		dt = dt.withHourOfDay(this.getStartTime().getHourOfDay());
		dt = dt.withMinuteOfHour(this.getStartTime().getMinuteOfHour());
		return dt.toDate();
	}
}


