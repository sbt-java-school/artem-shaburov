package homework6;

/**
 * Created by art
 */
public class To {
    private int intField;
    private String stringField;
    private String anotherString;
    private Object someObject;
    private boolean isLoaded;

    public void setIntField(int intField) {
        this.intField = intField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public void setAnotherString(String anotherString) {
        this.anotherString = anotherString;
    }

    public void setSomeObject(Object someObject) {
        this.someObject = someObject;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    @Override
    public String toString() {
        return "To{" +
                "intField=" + intField +
                ", stringField='" + stringField + '\'' +
                ", anotherString='" + anotherString + '\'' +
                ", someObject=" + someObject +
                ", isLoaded=" + isLoaded +
                '}';
    }
}
