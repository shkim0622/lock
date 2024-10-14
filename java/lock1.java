import java.util.concurrent.locks.ReentrantLock;

class Lock1 implements Runnable {
    ReentrantLock mutex = new ReentrantLock();
    int cnt = 0;
    @Override
    public void run() {
        mutex.lock();
        try {
            // Critical section
            //cnt = 0;
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+ "  : " + cnt);
                cnt++;
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
        mutex.unlock(); 
    }
}
public class lock1 {
    public static void main(String[] args) {
        Lock1 lock1_ex = new Lock1();

        // 스레드 생성 및 시작
        Thread thread1 = new Thread(lock1_ex);
        Thread thread2 = new Thread(lock1_ex);

        thread1.start();
        thread2.start();
        
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
