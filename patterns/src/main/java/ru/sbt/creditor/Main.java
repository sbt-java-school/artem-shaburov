package ru.sbt.creditor;

import ru.sbt.creditor.operations.AndNode;
import ru.sbt.creditor.operations.DivideNode;
import ru.sbt.creditor.operations.EqualsNode;
import ru.sbt.creditor.operations.LowerThanNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author artem
 */
public class Main {

    // (city == "Novosibirsk") && (amountOfCredit / periodOfMonths < salary / 2)

    private Map<String, Double> values = new HashMap<>();
    {
        values.put("Novosibirsk", 54d);
        values.put("RequiredCity", 54d);
        values.put("AmountOfCredit", 100_000d);
        values.put("PeriodOfMonths", 10d);
        values.put("Salary", 25_000d);
        values.put("Divider", 2d);
    }

    public static void main(String[] args) {
        new Main().test();
    }

    private void test() {
        Node city = new ParameterNode("Novosibirsk");
        Node requiredCity = new ParameterNode("RequiredCity");
        Node amountOfCredit = new ParameterNode("AmountOfCredit");
        Node periodOfMonths = new ParameterNode("PeriodOfMonths");
        Node salary = new ParameterNode("Salary");
        Node divider = new ParameterNode("Divider");

        Node cityEquals = new EqualsNode();
        cityEquals.add(city);
        cityEquals.add(requiredCity);

        Node creditInMonth = new DivideNode();
        creditInMonth.add(amountOfCredit);
        creditInMonth.add(periodOfMonths);

        Node halfOfSalary = new DivideNode();
        halfOfSalary.add(salary);
        halfOfSalary.add(divider);

        Node creditLowerThanNeeded = new LowerThanNode();
        creditLowerThanNeeded.add(creditInMonth);
        creditLowerThanNeeded.add(halfOfSalary);

        Node result = new AndNode();
        result.add(cityEquals);
        result.add(creditLowerThanNeeded);

        String decision = result.getResult(values) == 1d ?
                "We can give you a credit." : "We can't give you a credit.";
        System.out.println(decision);
    }
}
