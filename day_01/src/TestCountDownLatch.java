import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.CountDownLatch;

/**
 * 使用闭锁，统计多个线程执行的时间和
 */
public class TestCountDownLatch {

    public static void main(String[] args) {
        final int COUNT = 6;
        // 1、创建闭锁
        CountDownLatch countDownLatch = new CountDownLatch(COUNT);
        LatchDemo latchDemo = new LatchDemo(countDownLatch);
        LocalTime startTime = LocalTime.now();
        for (int j = 0; j < COUNT; j++) {
            new Thread(latchDemo).start();
        }
        try {
            // 2、等待线程全部执行
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行时间之和：" + Duration.between(startTime, LocalTime.now()).toMillis()+" 毫秒");
    }
}

class LatchDemo implements Runnable{

    CountDownLatch countDownLatch;

    public LatchDemo(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "在执行...");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 3、执行完一个线程就减一
            countDownLatch.countDown();
        }
    }
}