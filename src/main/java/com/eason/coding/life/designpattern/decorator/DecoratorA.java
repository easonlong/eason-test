package com.eason.coding.life.designpattern.decorator;

public class DecoratorA implements Component {

	private Component component;

	public DecoratorA(Component component) {
		super();
		this.component = component;
	}

	@Override
	public void operation() {
		component.operation();
		System.out.println("I am doing the extra thing A");
	}

}
