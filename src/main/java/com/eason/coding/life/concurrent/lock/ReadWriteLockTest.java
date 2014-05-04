package com.eason.coding.life.concurrent.lock;

import org.springframework.beans.BeansException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.eason.coding.life.ApplicationContextManager;

public class ReadWriteLockTest {
	private static final String[] APPLICATION_CONTEXT_PATH = { "spring/spring-concurrent-lock.xml" };

	public static void main(String[] args) {
		ApplicationContextManager.init(APPLICATION_CONTEXT_PATH);
		try {
			ReadWriteResource resource = (ReadWriteResource) ApplicationContextManager
					.getInstance().getBean("readWriteResoure");
			ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) ApplicationContextManager
					.getInstance().getBean("defaultTaskExecutor");
			taskExecutor.execute(new ReadTask(resource));
			taskExecutor.execute(new WriteTask(resource));
			taskExecutor.execute(new ReadTask(resource));
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
