package com.eason.coding.life.concurrent.lock;

import org.apache.log4j.Logger;

public class WriteTask implements Runnable {
	private static final Logger LOGGER = Logger.getLogger(WriteTask.class);
	private ReadWriteResource resource;

	public WriteTask(ReadWriteResource resource) {
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
		LOGGER.info("WriteTask begin to run..");
		resource.write();
		LOGGER.info("WriteTask finish run..");
	}

}
