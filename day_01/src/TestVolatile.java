/**
 * 验证内存可见性问题
 */
public class TestVolatile {

    public static void main(String[] args) {

        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo).start();

        while (true) {
            if(threadDemo.isFlag()){
                System.out.println("主线程");
            }
        }

    }
}
class ThreadDemo implements Runnable{
    private boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag>>" + flag);

    }
}