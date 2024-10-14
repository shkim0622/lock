import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class PrintThreadId implements Runnable {
    private static final Lock mutex = new ReentrantLock(); // 뮤텍스 객체 생성
    
    private final int id;

    public PrintThreadId(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        mutex.lock(); // 뮤텍스 객체를 잠근다.
        System.out.println("thread #" + id);
        mutex.unlock(); // 뮤텍스 객체를 잠금 해제한다.
    }
}

public class lock2 {
    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new PrintThreadId(i));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}