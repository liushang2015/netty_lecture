package com.liushang.decorator;

public class ConcreteDecorator1 extends Decorator {

    public ConcreteDecorator1(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnotherTing();

    }

    private void doAnotherTing(){
        System.out.println("¹¦ÄÜB");
    }
}
