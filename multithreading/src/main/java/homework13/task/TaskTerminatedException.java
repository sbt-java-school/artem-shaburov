package homework13.task;

/**
 * @author artem
 */
public class TaskTerminatedException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "Task terminated";

    public TaskTerminatedException() {
        this(DEFAULT_MESSAGE);
    }

    public TaskTerminatedException(String message) {
        super(message);
    }

}
