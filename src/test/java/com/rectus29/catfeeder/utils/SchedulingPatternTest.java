package com.rectus29.catfeeder.utils;

import org.joda.time.LocalTime;
import org.junit.jupiter.api.Test;

import java.util.Date;

/*-----------------------------------------------------*/
/*                     Adelya                          */
/*                                                     */
/*                   Date: 18/02/2019                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
class SchedulingPatternTest {

	@Test
	void getNextExecutionDate() {
		SchedulingPattern pattern = new SchedulingPattern();
		pattern.setDayNumber(5);
		pattern.setStartTime(new LocalTime().withHourOfDay(12).withMinuteOfHour(0));
		pattern.setEndTime(new LocalTime().withHourOfDay(0).withMinuteOfHour(0));

		pattern.getNextExecutionDate(new Date());
	}
}