/**
 * 模拟CAS算法
 */
public class TestCompareAndSwap {
    public static void main(String[] args) {
        final CompareAndSwap cas = new CompareAndSwap();
        for (int j = 0; j < 10; j++) {
            new Thread(()-> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int expectedVale = cas.get();
                boolean b = cas.compareAndSet(expectedVale, (int) (Math.random() * 101));
                System.out.println("b>>>" + b);
            }).start();
        }
    }
}
class CompareAndSwap{

    private int value;
    public int get() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedVale, int newValue) {

        int oldValue = value;
        if (oldValue == expectedVale) {
            this.value = newValue;
        }
        return oldValue;

    }
    public synchronized boolean compareAndSet(int expectedVale,int newValue) {
        return expectedVale == compareAndSwap(expectedVale, newValue);
    }

}