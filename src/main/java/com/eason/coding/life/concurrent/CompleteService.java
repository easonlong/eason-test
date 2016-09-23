package com.eason.coding.life.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompleteService {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		new CompleteService().run();
	}
	
	public void run() throws InterruptedException, ExecutionException{
		ExecutorService executor = Executors.newFixedThreadPool(10);
		CompletionService<String> executorService = new ExecutorCompletionService<String>(executor);
		executorService.submit(new FutureTask(false));
		executorService.submit(new FutureTask(false));
		executorService.take().get();
		executorService.take().get();
		executorService.take().get();
		System.out.println("end");
	}

	private class FutureTask implements Callable<String>{
		
		private final boolean flag;
		
		public FutureTask(boolean flag) {
			this.flag = flag;
		}

		@Override
		public String call() throws Exception {
			if(this.flag){
				throw new Exception("error");
			}
			Thread.sleep(1000);
			return "hello";
		}
		
	}
}
