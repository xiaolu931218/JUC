import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class TestReadWriteLock {
    public static void main(String[] args) {
        ReadWriteLockDemo demo = new ReadWriteLockDemo();
        new Thread(()->{
            demo.write(new Random().nextInt());
        },"写").start();
        for (int j = 0; j < 3; j++) {
            new Thread(()->{
                demo.read();
            },"读").start();
        }
    }
}

class ReadWriteLockDemo {
    private int number =0;
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void read() {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": " + number);
        }finally {
            readWriteLock.readLock().unlock();
        }
    }
    public void write(int number) {
        readWriteLock.writeLock().lock();
        try {
            this.number = number;

            System.out.println(Thread.currentThread().getName() + "  ");
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }
}