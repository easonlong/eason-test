package com.eason.coding.life.designpattern.proxy;

public class Client {

    public static void main(String args[]) {
        Subject subject = new Proxy();
        subject.doSomething();
    }
}
