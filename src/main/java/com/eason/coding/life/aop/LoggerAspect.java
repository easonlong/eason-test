package com.eason.coding.life.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggerAspect {
	private final Logger LOGGER = Logger.getLogger(LoggerAspect.class);

	@Pointcut("execution(* com.eason.coding.life.aop.*.doService(..))")
	private void pointCutMethod() {
	}

	@Before("execution(* com.eason.coding.life.aop.*.doService(..))")
	public void beforeService() {
		LOGGER.info("logger aspect: before service.");
	}

	@After("pointCutMethod()")
	public void afterService() {
		LOGGER.info("logger aspect: after service.");
	}
}
