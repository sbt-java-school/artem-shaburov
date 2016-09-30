package homework11;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author artem
 */
public class TestStreams {

    private static List<Person> persons = Arrays.asList(
            new Person("Anton", "Ivanov"),
            new Person("Arthur", "Ivanov"),
            new Person("Andrey", "Ivanov"),
            new Person("Ivan", "Semyonov"),
            new Person("Ivan", "Vasilyev")
    );

    @Test
    public void test() {
        System.out.println("original collection");
        System.out.println(Streams.of(persons).transform(Person::getFirstName).getCollection());

        Streams<Person> stream = Streams.of(persons);
        Map<String, Person2> resultMap = stream
                .filter(person -> person.getFirstName().startsWith("A"))
                .transform(person -> new Person2(person.getFirstName()))
                .toMap(Person2::getName, person2 -> person2);
        System.out.println("result map");
        System.out.println(resultMap.keySet());

        List<Person> originalCollection = stream.getCollection();
        System.out.println("original collection");
        System.out.println(Streams.of(originalCollection).transform(Person::getFirstName).getCollection());

        Assert.assertEquals(persons, originalCollection);
    }
}
