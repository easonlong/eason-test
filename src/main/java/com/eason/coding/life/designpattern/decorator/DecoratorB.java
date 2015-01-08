package com.eason.coding.life.designpattern.decorator;

public class DecoratorB implements Component {

	private Component component;

	public DecoratorB(Component component) {
		super();
		this.component = component;
	}

	@Override
	public void operation() {
		component.operation();
		System.out.println("I am doing the extra thing B");
	}

}

