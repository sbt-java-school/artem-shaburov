package homework13;

import homework13.manager.ExecutionManager;
import homework13.manager.ExecutionManagerImpl;

import java.util.concurrent.TimeUnit;

/**
 * @author artem
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        ExecutionManager executionManager = new ExecutionManagerImpl();
        executionManager.execute(
                main.timerTask(1),
                main.timerTask(1), main.timerTask(2), main.timerTask(3)
        );
    }

    private Runnable timerTask(int seconds) {
        return () -> {
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }
}
