package homework12;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @author artem
 */
public class MyThread extends Thread {
    /** очередь наших задач */
    private final LinkedList<Runnable> tasks;
    /** булин для остановки данного треда */
    private volatile boolean isStopped;

    /** конструктор принимает очередь */
    public MyThread(LinkedList<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        main:
        while (true) {
            synchronized (tasks) {
                while (tasks.isEmpty()) {
                    //System.out.println("queue is empty");
                    if (isStopped) {
                        break main;
                    }
                    try {
                        tasks.wait();
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            try {
                Runnable currentTask = tasks.removeFirst();
                currentTask.run();
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(Thread.currentThread().getName() + " stopped");
    }

    /** с помощью этой ф-ции останавливаем тред */
    public void stopThread() {
        isStopped = true;
    }
}
