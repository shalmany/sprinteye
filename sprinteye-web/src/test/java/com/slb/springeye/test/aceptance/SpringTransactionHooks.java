package com.slb.springeye.test.aceptance;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import cucumber.api.java.After;
import cucumber.api.java.Before;

@WebAppConfiguration
@ContextConfiguration("classpath:cucumber.xml")
public class SpringTransactionHooks {

	@Resource
	private ApplicationContext ctx;
	@Resource
	private PlatformTransactionManager transactionManager;
	private TransactionStatus transactionStatus;

	@Before(value = { "@txn" }, order = 100)
	public void startTransaction() {
		transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
	}

	@After(value = { "@txn" }, order = 100)
	public void rollBackTransaction() {
		transactionManager.rollback(transactionStatus);
	}

}
