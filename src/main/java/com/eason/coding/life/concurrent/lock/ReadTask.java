package com.eason.coding.life.concurrent.lock;

import org.apache.log4j.Logger;

public class ReadTask implements Runnable {
	private static final Logger LOGGER = Logger.getLogger(ReadTask.class);
	private ReadWriteResource resource;

	public ReadTask(ReadWriteResource resource) {
		this.resource = resource;
	}

	public ReadWriteResource getResource() {
		return resource;
	}

	public void setResource(ReadWriteResource resource) {
		this.resource = resource;
	}

	@Override
	public void run() {
		LOGGER.info("ReadTask begin to run..");
		resource.read();
		LOGGER.info("ReadTask finish run..");
	}

}
