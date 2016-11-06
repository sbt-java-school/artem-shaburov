package ru.sbt.bit.ood.solid.homework;

import org.mockito.ArgumentCaptor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import ru.sbt.bit.ood.solid.homework.dao.SalaryPaymentDao;
import ru.sbt.bit.ood.solid.homework.dao.SalaryPaymentDaoImpl;
import ru.sbt.bit.ood.solid.homework.services.Sender;
import ru.sbt.bit.ood.solid.homework.services.SenderImpl;
import ru.sbt.bit.ood.solid.homework.utils.builders.ReportBuilder;
import ru.sbt.bit.ood.solid.homework.utils.builders.ReportBuilderImpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

/**
 * @author artem
 */
class SendReportTest {
    private Connection connection;

    SendReportTest(Connection connection) {
        this.connection = connection;
    }

    void sendTest() throws Exception {
        // initialize things
        SalaryPaymentDao salaryPaymentDao = new SalaryPaymentDaoImpl(connection);
        ReportBuilder reportBuilder = new ReportBuilderImpl();
        Sender sender = new SenderImpl();

        // set up parameters
        SalaryHtmlReportNotifier notificator = new SalaryHtmlReportNotifier(salaryPaymentDao, reportBuilder, sender);
        LocalDate dateFrom = LocalDate.of(2014, Month.JANUARY, 1);
        LocalDate dateTo = LocalDate.of(2014, Month.DECEMBER, 31);

        // mock mail related stuff
        MimeMessageHelper mockMimeMessageHelper = getMockedMimeMessageHelper();

        // execute
        notificator.generateAndSendHtmlSalaryReport("10", dateFrom, dateTo, "somebody@gmail.com");

        // verify results
        String expectedReportPath = "/home/artyom/Documents/IdeaProjects/artem-shaburov/refactoring/src/test/resources/expectedReport.html";
        assertActualReportEqualsTo(mockMimeMessageHelper, expectedReportPath);
    }

    private void assertActualReportEqualsTo(MimeMessageHelper mockMimeMessageHelper, String expectedReportPath) throws MessagingException, IOException {
        ArgumentCaptor<String> messageTextArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockMimeMessageHelper).setText(messageTextArgumentCaptor.capture(), anyBoolean());
        Path path = Paths.get(expectedReportPath);
        String expectedReportContent = new String(Files.readAllBytes(path));
        assertEquals(messageTextArgumentCaptor.getValue(), expectedReportContent);
    }

    private MimeMessageHelper getMockedMimeMessageHelper() throws Exception {
        JavaMailSenderImpl mockMailSender = mock(JavaMailSenderImpl.class);
        MimeMessage mockMimeMessage = mock(MimeMessage.class);
        when(mockMailSender.createMimeMessage()).thenReturn(mockMimeMessage);
        whenNew(JavaMailSenderImpl.class).withNoArguments().thenReturn(mockMailSender);
        MimeMessageHelper mockMimeMessageHelper = mock(MimeMessageHelper.class);
        whenNew(MimeMessageHelper.class).withArguments(any()).thenReturn(mockMimeMessageHelper);
        return mockMimeMessageHelper;
    }
}
