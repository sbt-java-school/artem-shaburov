package homework13.task;

import java.util.concurrent.Callable;

/**
 * @author artem
 */
public class Task<T> {

    private T result;
    private Callable<? extends T> callable;
    private RuntimeException taskTerminatedException;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() {
        if (taskTerminatedException != null) {
            throw taskTerminatedException;
        }
        if (result == null) {
            synchronized (this) {
                try {
                    result = callable.call();
                } catch (Exception e) {
                    taskTerminatedException = new TaskTerminatedException();
                    throw taskTerminatedException;
                }
            }
        }
        return result;
    }

}
