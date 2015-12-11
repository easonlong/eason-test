package com.eason.coding.life.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



public class ThreadPoolExecutorTest {
	public static void main(String[] args) {
		 ThreadPoolExecutor threadPoolTaskExecutor = new ThreadPoolExecutor(2, 4, 30, 
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4),  
				new RejectedExecutionHandler() {
					@Override
			        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				        if (!executor.isShutdown()) {
					        try {
					        	System.out.println("Task was rejected");
		                        executor.getQueue().put(r);
	                        } catch (Throwable e) {
	                        	System.out.println("Catched Throwable when put the task into queue:"+e);
	                        }
				        }
			        }
				});
	    for(int i=0;i<20;i++){
	    	System.out.println("Create Thread"+i);
	    	threadPoolTaskExecutor.execute(new Runnable() {
				
				@Override
				public void run() {
					
					System.out.println(Thread.currentThread().getName());
					try {
	                    Thread.sleep(10000);
                   } catch (InterruptedException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
                   }
				}
			});
	    }
   }
}
