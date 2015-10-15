package com.eason.coding.life.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.springframework.beans.factory.FactoryBean;


public class ProxyServiceFactory<T> implements FactoryBean<T>{

	private Class<T> service;
	public ProxyServiceFactory(Class<T> service) {
	    super();
	    this.service = service;
    }

	private CglibProxy cglibProxy=new CglibProxy();
	
	public T getObject() throws Exception {
	    return cglibProxy.getProxy(service);
    }

	public Class<?> getObjectType() {
	    return service;
    }

	public boolean isSingleton() {
	    return true;
    }
	
	private class CglibProxy implements MethodInterceptor {

		private Enhancer enhancer = new Enhancer();

		@SuppressWarnings("unchecked")
		public T getProxy(Class<T> clazz) {
			this.enhancer.setCallback(this);
			this.enhancer.setSuperclass(clazz);
			return (T) this.enhancer.create();
		}

		public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
			System.out.println("Before service");
			return  proxy.invokeSuper(obj, args);
		}

	}


}
