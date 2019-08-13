package com.rectus29.catfeeder.task;

import com.pi4j.io.gpio.*;
import com.rectus29.catfeeder.utils.Mp3Player;
import com.rectus29.catfeeder.utils.SchedulingPattern;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.UUID;

/*-----------------------------------------------------*/
/*                     Rectus29                        */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

public class CatFeedTask extends TimerTask {
	private static Logger logger = LogManager.getLogger(CatFeedTask.class);
	private static int MOTORRUNNINGMILLIS = 2000;
	private int quantity = 10;
	private List<SchedulingPattern> schedulingPatterns = new ArrayList<>();
	private String uid = UUID.randomUUID().toString();

	@Override
	public void run() {
		logger.info("Start Distribution");
		try {
			// write your code here
			GpioController gpioController = GpioFactory.getInstance();
			GpioPinDigitalOutput motorRelayCommand = gpioController.provisionDigitalOutputPin(
					RaspiPin.GPIO_07,
					"motorRelayCommand",
					PinState.LOW
			);

			new Mp3Player().play("plop.mp3");
			logger.info("distribution inprogress");
			motorRelayCommand.setState(PinState.HIGH);
			this.wait(MOTORRUNNINGMILLIS);
			motorRelayCommand.setState(PinState.LOW);
			logger.info("distribution done");
		} catch (InterruptedException e) {
			logger.info("distribution error", e);
		}
	}

	public List<SchedulingPattern> getSchedulingPatterns() {
		return schedulingPatterns;
	}

	public CatFeedTask setSchedulingPatterns(List<SchedulingPattern> schedulingPatterns) {
		this.schedulingPatterns = schedulingPatterns;
		return this;
	}

	public int getQuantity() {
		return quantity;
	}

	public CatFeedTask setQuantity(int quantity) {
		this.quantity = quantity;
		return this;
	}

	public String getUid() {
		return uid;
	}

	public CatFeedTask setUid(String uid) {
		this.uid = uid;
		return this;
	}
}
