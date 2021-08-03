import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 使用ConcurrentHashMap
 */
public class TestHashMap {
    public static void main(String[] args) {
        HashMapRunnable hashMapRunnable = new HashMapRunnable();

        for (int k = 0; k < 5; k++) {
            new Thread(hashMapRunnable).start();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打印结果
        hashMapRunnable.printMap();
    }



}

class HashMapRunnable implements Runnable {
    private Map<String, String> map = new ConcurrentHashMap<>();

    @Override
    public void run() {
        for (int j = 0; j < 5; j++) {
            map.put(j + "", Thread.currentThread().getName());
        }
    }

    public void printMap() {
        map.forEach((key,value) ->{
            System.out.println(key + " : " + value);
        });
    }




}