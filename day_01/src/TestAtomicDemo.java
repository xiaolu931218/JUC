import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用原子变量
 */
public class TestAtomicDemo {

    public static void main(String[] args) {

        AtmicDemo atmicDemo = new AtmicDemo();

        for (int i = 0; i < 10; i++) {
            new Thread(atmicDemo).start();
        }
    }

}

class AtmicDemo implements Runnable {
    private AtomicInteger serialNumber = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+": " + serialNumber.getAndIncrement());
    }
}