package org.gumplab.design.singleton;

// 饿汉式写法
public class Singleton_e {

    private static Singleton_e singleton = new Singleton_e();

    //线程安全
    public static Singleton_e getInstance(){
        return singleton;
    }

}
