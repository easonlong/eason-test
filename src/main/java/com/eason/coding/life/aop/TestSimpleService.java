package com.eason.coding.life.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestSimpleService {
	private static final String[] APPLICATION_CONTEXT_PATH = { "spring/spring-aop-aspectj.xml" };

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
				APPLICATION_CONTEXT_PATH);
		Runtime.getRuntime().addShutdownHook(new Shutdown(ac));
		Service service = (Service) ac.getBean("service");
		service.doService("Hello, World!");
	}
	private static void wait(ClassPathXmlApplicationContext context) throws InterruptedException {
		synchronized (context) {
			while (context.isActive()) {
				System.out.println("wait");
				context.wait();
			}
		}
	}
	private static class Shutdown extends Thread {

		final ClassPathXmlApplicationContext context;

		public Shutdown(ClassPathXmlApplicationContext context) {
			super();
			this.context = context;
		}

		@Override
		public void run() {
			synchronized (this.context) {
				try {
	                Thread.sleep(1000);
                } catch (InterruptedException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
                }
				System.out.println("shutdown");
				// 先关闭后唤醒
				this.context.close();
				this.context.notifyAll();
			}
		}
	}
}
