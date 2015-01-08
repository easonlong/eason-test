package com.eason.coding.life.designpattern.adapter;

public class Adapter implements Target {
    private Adaptee1 adaptee1;
    private Adaptee2 adaptee2;

    public Adapter(Adaptee1 adaptee1, Adaptee2 adaptee2) {
        this.adaptee1 = adaptee1;
        this.adaptee2 = adaptee2;
    }

    @Override
    public void doSomething() {
        adaptee1.doSomething1();
        adaptee2.doSomething2();
    }

}
