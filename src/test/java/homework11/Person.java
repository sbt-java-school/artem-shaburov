package homework11;

import java.util.Date;

/**
 * @author artem
 */
public class Person {
    private String firstName;
    private String lastName;
    private Date birthday;
    private int age;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        Date now = new Date();
        // calculate age
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
