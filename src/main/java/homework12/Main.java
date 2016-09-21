package homework12;

import java.util.concurrent.TimeUnit;

/**
 * @author artem
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.test();
    }

    public void test() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool(5);
        for (int i = 0; i < 3; i++) {
            executeTask(threadPool, 1);
        }
        TimeUnit.SECONDS.sleep(3);
        for (int i = 0; i < 10; i++) {
            executeTask(threadPool, 2);
        }
        threadPool.stopThreads();
    }

    private void executeTask(ThreadPool threadPool, int seconds) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " got a new task");
                try {
                    TimeUnit.SECONDS.sleep(seconds);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("task is ended in " + Thread.currentThread().getName());
            }
        });
    }
}
