package com.eason.coding.life.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

public class ReadWriteResource {
	private static final Logger LOGGER = Logger
			.getLogger(ReadWriteResource.class);
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	private Lock readLock = lock.readLock();
	private Lock writeLock = lock.writeLock();

	public void read() {

		readLock.lock();
		LOGGER.info("begin to read, add lock...");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			LOGGER.info("finish read, release lock...");
			readLock.unlock();
		}
	}

	public void write() {

		writeLock.lock();
		LOGGER.info("begin to write, add lock...");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			LOGGER.info("finish write, release lock...");
			writeLock.unlock();
		}
	}
}
