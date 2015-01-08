package com.eason.coding.life.designpattern.proxy;

public class RealSubject implements Subject{

    @Override
    public void doSomething() {
        System.out.println("I am doing the real thing");        
    }

}
