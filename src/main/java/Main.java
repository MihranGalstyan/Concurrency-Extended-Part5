import java.util.*;
import java.util.concurrent.*;

/**
 * Created by Mihran Galstyan
 * All rights reserved
 */
public class Main {
    public static void main(final String[] args) {
//        Object monitor = new Object();
        List<Integer> numbers = Collections.synchronizedList(new ArrayList<>());

        //Low speed synchronized collections
//        Set<Integer> newSet = Collections.synchronizedSet(new HashSet<>());
//        Map<Integer,String> newMap = Collections.synchronizedMap(new HashMap<>());


        //Optimized synchronized collections
//        List<Integer> synchronizedList = new CopyOnWriteArrayList<>();
//        Set<Integer> synchronizedSet = new CopyOnWriteArraySet<>();
//        Map<Integer,String> synchronizedMap = new ConcurrentHashMap<>();

        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        Thread.sleep(100);
//                        synchronized (monitor) {
                        numbers.add(i);
//                        }
                    }
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        Thread.sleep(100);
//                        synchronized (monitor) {
                        numbers.add(i);
//                        }
                    }
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (Integer number : numbers) {
            System.out.println(number);
        }
        System.out.println(numbers.size());
    }
}
