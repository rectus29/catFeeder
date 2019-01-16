package com.rectus29.catfeeder;

import com.rectus29.catfeeder.utils.SchedulingPattern;

/*-----------------------------------------------------*/
/*                     Rectus29                        */
/*                                                     */
/*                   Date: 16/01/2019                  */
/*                 All right reserved                  */
/*-----------------------------------------------------*/
public class CatFeederScheduleEntry {

	private SchedulingPattern pattern;
	private Double quantity = 10d;

	protected CatFeederScheduleEntry() {
	}

	public CatFeederScheduleEntry(SchedulingPattern pattern, Double quantity) {
		this.pattern = pattern;
		this.quantity = quantity;
	}

	public SchedulingPattern getPattern() {
		return pattern;
	}

	public CatFeederScheduleEntry setPattern(SchedulingPattern pattern) {
		this.pattern = pattern;
		return this;
	}

	public Double getQuantity() {
		return quantity;
	}

	public CatFeederScheduleEntry setQuantity(Double quantity) {
		this.quantity = quantity;
		return this;
	}
}
