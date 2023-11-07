package com.urise.webapp.util;

public class LazySingleton {

    volatile private static LazySingleton INSTANCE;

    private LazySingleton() {
    }

//    Первый способ - не норм
//    Не годится для многопоточки тк сразу несколько потоков одеовременно могу попасть в блок if и создать несколько инстансов
//    private static LazySingleton getInstance() {
//        if (instance == null) {
//            instance = new LazySingleton();
//        }
//        return instance;
//    }

//    Второй способ способ норм но длинный
//    private static LazySingleton getInstance() {
//        if (instance == null) {
//            synchronized (LazySingleton.class) {
//                if (instance == null) {
//                    instance = new LazySingleton();
//                }
//            }
//        }
//        return instance;
//    }


//    Третий способ
    private static class LazySingletonHolder {
        private static final LazySingleton INSTANCE = new LazySingleton();
    }

    public static LazySingleton getInstance() {
        return LazySingleton.INSTANCE;
    }
}
