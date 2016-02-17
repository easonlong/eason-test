package com.eason.coding.life.concurrent;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class CountdownLatchTest {

	private CountDownLatch startLatch = new CountDownLatch(1);

	private CountDownLatch endLatch = new CountDownLatch(2);

	@Test
	public void test() throws InterruptedException {
		new Thread(new SubTask()).start();
		new Thread(new SubTask()).start();
		startLatch.countDown();// Begin to execute tasks
		endLatch.await();// wait until all the tasks been executed
		System.out.println("All tasks are finished");
	}

	class SubTask implements Runnable {

		@Override
		public void run() {
			try {
				startLatch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("execute task");
			endLatch.countDown();
		}

	}
}
