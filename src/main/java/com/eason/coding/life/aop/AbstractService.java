package com.eason.coding.life.aop;

public abstract class AbstractService implements Service{

	@Override
	public void doService(String message) {
		preProcess();
		process();
		postProcess();
		
	}
	abstract void process();
	abstract void preProcess();
	abstract void postProcess();
}
