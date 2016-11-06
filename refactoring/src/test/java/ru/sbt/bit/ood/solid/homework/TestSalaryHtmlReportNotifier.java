package ru.sbt.bit.ood.solid.homework;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.sbt.bit.ood.solid.homework.services.SenderImpl;

import java.sql.Connection;

import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SalaryHtmlReportNotifier.class, SenderImpl.class})
public class TestSalaryHtmlReportNotifier {

    @Test
    public void test() throws Exception {
        Connection someFakeConnection = mock(Connection.class);
        DatabaseTest databaseTest = new DatabaseTest(someFakeConnection);
        databaseTest.connectionTest();
        SendReportTest sendReportTest = new SendReportTest(someFakeConnection);
        sendReportTest.sendTest();
    }

}
