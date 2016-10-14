package homework13.manager;

/**
 * @author artem
 */
public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
