package com.lxy.designmodal;

interface Car {
    void run();
}

class Audi implements Car {

    @Override
    public void run() {
        System.out.println("奥迪");
    }
}

class Benci implements Car {

    @Override
    public void run() {
        System.out.println("奔驰");
    }
}


class CarFactory {

    public static Car getCar(String name) {
        Car car = null;
        switch (name) {
            case "奥迪":
                car = new Audi();
                break;
            case "奔驰":
                car = new Benci();
                break;
        }

        return car;
    }
}

//工厂模式
public class Factory {

    public static void main(String[] args) {

        Car car = CarFactory.getCar("奔驰");
        car.run();
    }
}
