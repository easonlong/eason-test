package com.eason.coding.life;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;

public class TestDaemon implements Daemon{

    public static void main(String[] args) {
	Runtime.getRuntime().addShutdownHook(new Thread()
	{
		public void run()
		{
		    System.out.println("****************Starting shutdown hook*********************");
			
		}
	
	});

	System.out.println("execute the task");
    }

    @Override
    public void destroy() {
	
	System.out.println("destroying...");
    }

    @Override
    public void init(DaemonContext arg0) throws Exception {
	// TODO Auto-generated method stub
	System.out.println("initializing...");
    }

    @Override
    public void start() throws Exception {
	// TODO Auto-generated method stub
	System.out.println("start the task");
    }

    @Override
    public void stop() throws Exception {
	System.out.println("stop the task");
	
    }

}
