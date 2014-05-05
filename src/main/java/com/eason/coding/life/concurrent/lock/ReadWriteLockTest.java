package com.eason.coding.life.concurrent.lock;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.TaskExecutor;

import com.eason.coding.life.ApplicationContextManager;

public class ReadWriteLockTest {
	private static final String[] APPLICATION_CONTEXT_PATH = { "spring/spring-concurrent-lock.xml", "spring/spring-context.xml" };

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_PATH);
		
		try {
			ReadWriteResource resource = (ReadWriteResource) ApplicationContextManager
					.getBean("readWriteResoure");
			TaskExecutor taskExecutor = (TaskExecutor) ApplicationContextManager
					.getBean("defaultTaskExecutor");
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
