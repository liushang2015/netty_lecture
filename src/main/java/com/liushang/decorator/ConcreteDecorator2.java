package com.liushang.decorator;

public class ConcreteDecorator2 extends Decorator {
    public ConcreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
//        this.doAnotherTing();
    }
    private void doAnotherTing(){
        System.out.println("¹¦ÄÜC");
    }
}
