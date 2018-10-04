package com.rectus29.catfeeder.task;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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

public class DummyTask implements Runnable {
	private static Logger logger = LogManager.getLogger(DummyTask.class);
	private static int MOTORRUNNINGMILLIS = 2000;

	@Override
	public void run() {
		logger.info("Start Distribution");
		System.out.println("plop");
	}
}
