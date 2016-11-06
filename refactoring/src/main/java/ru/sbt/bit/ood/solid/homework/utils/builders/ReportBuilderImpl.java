package ru.sbt.bit.ood.solid.homework.utils.builders;

import ru.sbt.bit.ood.solid.homework.entities.SalaryPayment;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by artem
 */
public class ReportBuilderImpl implements ReportBuilder {

    @Override
    public String buildPaymentReport(List<SalaryPayment> results) {
        // create a StringBuilder holding a resulting html
        StringBuilder resultingHtml = new StringBuilder();
        resultingHtml.append("<html><body><table><tr><td>Employee</td><td>Salary</td></tr>");
        double totals = 0;
        for (SalaryPayment salaryPayment : results) {
            // process each row of query results
            resultingHtml.append("<tr>"); // add row start tag
            resultingHtml.append("<td>").append(salaryPayment.getEmployeeName()).append("</td>"); // appending employee name
            resultingHtml.append("<td>").append(salaryPayment.getSalary()).append("</td>"); // appending employee salary for period
            resultingHtml.append("</tr>"); // add row end tag
            totals += salaryPayment.getSalary(); // add salary to totals
        }
        resultingHtml.append("<tr><td>Total</td><td>").append(totals).append("</td></tr>");
        resultingHtml.append("</table></body></html>");
        return resultingHtml.toString();
    }

}
