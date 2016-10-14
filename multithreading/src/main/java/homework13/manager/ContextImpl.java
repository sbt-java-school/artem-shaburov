package homework13.manager;

/**
 * @author artem
 */
public class ContextImpl implements Context {

    private int completedTaskCount;
    private int failedTaskCount;
    private int interruptedTaskCount;

    @Override
    public int getCompletedTaskCount() {
        return 0;
    }

    @Override
    public int getFailedTaskCount() {
        return 0;
    }

    @Override
    public int getInterruptedTaskCount() {
        return 0;
    }

    @Override
    public void interrupt() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
