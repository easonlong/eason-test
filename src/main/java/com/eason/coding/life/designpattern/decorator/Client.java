package com.eason.coding.life.designpattern.decorator;

public class Client {

	public static void main(String[] args) {
		Component component = new DecoratorB(new DecoratorA(new ConcreteComponent()));
		component.operation();

	}

}
