package homework12;

import java.util.*;

/**
 * класс принимает реализации runnable и выполняет их на фиксированном числе потоков
 * если есть свободный поток, то выполняет, если нет, то добавляет в очередь выполнения
 * реализуем это через очередь. будем передавать ее в каждый поток и внутри потока будем синхронизовать по ней же
 * при приходе нового runnable вызывать notify у очереди, чтобы сообщить об этом всем потокам
 *
 * @author artem
 */
public class ThreadPool {
    /** очередь задач */
    private final LinkedList<Runnable> tasks = new LinkedList<>();
    /** список наших собственных тредов */
    private List<MyThread> threads;

    /** конструктор принимает в качестве параметра количество потоков в пуле */
    public ThreadPool(int initialCapacity) {
        threads = new ArrayList<>(initialCapacity);
        // создаем запускаем столько потоков, сколько нам передано в параметре
        for (int i = 0; i < initialCapacity; i++) {
            MyThread thread = new MyThread(tasks);
            threads.add(thread);
            thread.start();
        }
    }

    /**
     * метод для выполнения новой задачи
     * @param task задача на выполнение
     */
    public void execute(Runnable task) {
        if (task == null) {
            return;
        }
        synchronized (tasks) {
            tasks.addLast(task);
            tasks.notify();
        }
    }

    /** с помощью этой ф-ции останавливаем все треды */
    public void stopThreads() {
        for (MyThread thread : threads) {
            thread.stopThread();
        }
    }
}
