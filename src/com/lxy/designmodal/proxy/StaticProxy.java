package com.lxy.designmodal.proxy;

interface House {
    void maifang();
}

class Buyer implements House {

    @Override
    public void maifang() {
        System.out.println("我买房");
    }
}

class Proxy implements House {

    private Buyer buyer;

    Proxy(Buyer buyer) {
        this.buyer = buyer;
    }

    @Override
    public void maifang() {
        System.out.println("start");
        buyer.maifang();
        System.out.println("end");
    }
}

public class StaticProxy {

    public static void main(String[] args) {
        Buyer buyer = new Buyer();
        Proxy proxy = new Proxy(buyer);
        proxy.maifang();
    }
}
