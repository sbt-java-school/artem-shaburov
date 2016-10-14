package homework13.manager;

import homework12.ThreadPool;

import java.util.concurrent.Callable;

/**
 * @author artem
 */
public class ExecutionManagerImpl implements ExecutionManager {

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        ThreadPool threadPool = new ThreadPool(tasks.length - 1);
        Context context = new ContextImpl();
        for (Runnable task : tasks) {
            threadPool.execute(task);
            // посчитать все для контекста
        }
        threadPool.execute(callback);
        return context;
    }

}
