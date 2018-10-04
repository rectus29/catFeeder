package com.rectus29.catfeeder.task;

import com.pi4j.io.gpio.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.TimerTask;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

public class CatFeedTask extends TimerTask {
	private static Logger logger = LogManager.getLogger(CatFeedTask.class);
	private static int MOTORRUNNINGMILLIS = 2000;

	@Override
	public boolean cancel() {
		return super.cancel();
	}

	@Override
	public long scheduledExecutionTime() {
		return super.scheduledExecutionTime();
	}

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
			logger.info("distribution inprogress");
			motorRelayCommand.setState(PinState.HIGH);
			this.wait(MOTORRUNNINGMILLIS);
			motorRelayCommand.setState(PinState.LOW);
			logger.info("distribution done");
		} catch (InterruptedException e) {
			logger.info("distribution error", e);
		}
	}
}
