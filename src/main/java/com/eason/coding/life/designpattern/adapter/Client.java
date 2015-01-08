package com.eason.coding.life.designpattern.adapter;

public class Client {

    public static void main(String[] args) {
        Target target = new Adapter(new Adaptee1(), new Adaptee2());
        target.doSomething();
    }

}
