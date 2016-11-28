package ru.sbt.cb;

import org.junit.Ignore;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

@Ignore
@ContextConfiguration(locations = {"/applicationContext.xml"})
@Rollback(false)
@Transactional(transactionManager = "transactionManager")
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {
}
