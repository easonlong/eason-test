package com.eason.coding.life.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {

	private Enhancer enhancer = new Enhancer();

	public Object getProxy(Class<?> clazz) {
		this.enhancer.setCallback(this);
		this.enhancer.setSuperclass(clazz);
		return this.enhancer.create();
	}

	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("Before service");
		return  proxy.invokeSuper(obj, args);
	}

}


