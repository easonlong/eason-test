package com.eason.coding.life.designpattern.proxy;

public class Proxy implements Subject{

    private RealSubject realSubject=new RealSubject();

    @Override
    public void doSomething() {
        realSubject.doSomething();
        System.out.println("I am doing the extra thing");      
    }
    
}
