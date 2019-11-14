package org.gumplab.design.singleton;

public class Singleton_lan {

    private static Singleton_lan singleton;

    // 线程不安全
    public static Singleton_lan getInstance_() {
        if (singleton == null){
            singleton = new Singleton_lan();
        }
        return singleton;
    }

    // 线程安全
    public static synchronized Singleton_lan getInstance() {
        if (singleton == null){
            singleton = new Singleton_lan();
        }
        return singleton;
    }

}
