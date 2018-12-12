package com.lxy.designmodal;


//单例模式
public class Singleton {

    //Singleton通过将构造方法限定为private避免了类在外部被实例化
    private Singleton() {
    }
    //饿汉模式
//    private static Singleton singleton = new Singleton();
//    private static Singleton getSingleton() {
//        return singleton;
//    }

    //懒汉模式(通过双重校验锁来保证线程安全) (但是在jvm优化环境下依然是不安全的，涉及到指令重排问题)
//    private static Singleton singleton;
//    private static Singleton getSingleton() {
//        if (singleton == null) {
//            synchronized (Singleton.class) {
//                if (singleton == null) {
//                    singleton = new Singleton();
//                }
//            }
//        }
//
//        return singleton;
//    }

    //static静态代码块实现
    private static Singleton single;
    // 静态代码块
    static{
        single = new Singleton();
    }
    private static Singleton getSingleton() {
        return single;
    }


    // 内部枚举类
//    private enum EnmuSingleton {
//        Singleton;
//        private Singleton singleton;
//
//        //枚举类的构造方法在类加载是被实例化
//        EnmuSingleton() {
//            singleton = new Singleton();
//        }
//
//        public Singleton getSingleton() {
//            return singleton;
//        }
//    }
//    private static Singleton getSingleton() {
//        return EnmuSingleton.Singleton.getSingleton();
//    }


    public static void main(String[] args) {

        Singleton s1 = Singleton.getSingleton();
        Singleton s2 = Singleton.getSingleton();

        System.out.println(s1 == s2);
    }
}
