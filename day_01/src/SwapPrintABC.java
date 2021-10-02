import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Condition进行线程之间通信，实现3个线程交替打印A，BB，CCCC
 */
public class SwapPrintABC {



    public static void main(String[] args) {
        AlternateDemo alternateDemo = new AlternateDemo();
        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    alternateDemo.loopA(i);
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                alternateDemo.loopB(i);
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                alternateDemo.loopC(i);
            }
            System.out.println("------------------------");
        },"C").start();
    }



}

class AlternateDemo {

    private int number = 1;

    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();


    public void loopA(int totalLoop)  {

        lock.lock();
        try {
            // 1、判断
            if (number != 1) {
                condition1.await();
            }
            //2、打印
            for (int j = 0; j < 1; j++) {
                System.out.println(Thread.currentThread().getName() + " A " + totalLoop);
            }
            // 3、唤醒
            number = 2;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void loopB(int totalLoop) {

        lock.lock();
        try {
            // 1、判断
            if (number != 2) {
                condition2.await();
            }
            //2、打印
            for (int j = 0; j < 2; j++) {
                System.out.println(Thread.currentThread().getName() + " B " + totalLoop);
            }
            // 3、唤醒
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void loopC(int totalLoop)  {

        lock.lock();
        try {
            // 1、判断
            if (number != 3) {
                condition3.await();
            }
            //2、打印
            for (int j = 0; j < 3; j++) {
                System.out.println(Thread.currentThread().getName() + " C " + totalLoop);
            }
            // 3、唤醒
            number = 1;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}