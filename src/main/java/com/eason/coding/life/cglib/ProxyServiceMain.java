package com.eason.coding.life.cglib;

public class ProxyServiceMain {
	public static void main(String[] args) throws Exception {
		/*CglibProxy proxy = new CglibProxy();
		TargetService service = (TargetService) proxy.getProxy(TargetService.class);
		service.doService();
		service.doService2();*/
		
		ProxyServiceFactory<TargetService> proxyServiceFactory=new ProxyServiceFactory<TargetService>(TargetService.class);
		TargetService service = proxyServiceFactory.getObject();
		service.doService();
		service.doService2();
	}
}
