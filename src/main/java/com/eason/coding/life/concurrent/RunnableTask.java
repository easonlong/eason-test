package com.eason.coding.life.concurrent;

import org.apache.log4j.Logger;

/**
 * @author Eason
 *
 */
public class RunnableTask implements Runnable {
	private final Logger LOGGER = Logger.getLogger(RunnableTask.class);

	@Override
	public void run() {
		LOGGER.info("I am "+Thread.currentThread().getName());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
