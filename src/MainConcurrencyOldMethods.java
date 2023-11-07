import java.util.ArrayList;
import java.util.List;

public class MainConcurrencyOldMethods {
    public static final int THREADS_NUMBER = 10000;
    private static int counter1;
    private static int counter2;
    private static int counter3;
    private static int counter4;
    private static int counter5;

    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        //После рана тред заканчивает свою работы и чистится гарбадж коллектором
        //Лучще использовать второй способ через Runnable
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + " " + getState());
            }
        };
        thread1.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState());
            }
        }).start();

        System.out.println(thread1.getState());

        //Все в одном потоке - не подохдит
        for (int i = 0; i < THREADS_NUMBER; i++) {
            for (int j = 0; j < 100; j++) {
                counter1++;
            }
        }
        System.out.println(counter1);

//        solution2();
//        solution3();
//        solution4();

    }

    //    счетчик будет не правильный тк потоки будут мешаться
    private static void solution1() {
        for (int i = 0; i < THREADS_NUMBER; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        counter2++;
                    }
                }
            }).start();

        }
        System.out.println(counter2);
    }

    //не подходит тк на консоль выведется counter3 до того как отоработют методв в solution2
    //можно закостылить но это не решение Thread.sleep(500);
    private static void solution2() throws InterruptedException {
        for (int i = 0; i < THREADS_NUMBER; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        inc();
                    }
                }
            }).start();
        }
        Thread.sleep(500);
        System.out.println(counter3);
    }

    //synchronized метода - в этот метод будет входить только один поток
    private static synchronized void inc() {
        counter3++;
    }

    private static void solution3() {
        for (int i = 0; i < THREADS_NUMBER; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        inc2();
                    }
                }
            }).start();
        }
        System.out.println("counter4 = " + counter4);
    }

    //тут стнхронизация идеёт по объекту который еще создался до многопоточки
    private static void inc2() {
        synchronized (LOCK) {
            counter4++;
        }
    }

    private static void solution4() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        inc3();
                    }
                }
            });
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("counter4 = " + counter5);
    }

    private static synchronized void inc3() {
        counter5++;
    }
}