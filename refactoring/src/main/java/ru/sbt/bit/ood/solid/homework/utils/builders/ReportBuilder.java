package ru.sbt.bit.ood.solid.homework.utils.builders;

import ru.sbt.bit.ood.solid.homework.entities.SalaryPayment;

import java.sql.SQLException;
import java.util.List;

/**
 * @author artyom
 */
public interface ReportBuilder {
    String buildPaymentReport(List<SalaryPayment> results);
}
