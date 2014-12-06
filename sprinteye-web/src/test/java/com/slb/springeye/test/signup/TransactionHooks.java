package com.slb.springeye.test.signup;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class TransactionHooks {

	private TransactionStatus txStatus;

	/* @Autowired
	private BeanFactory beanFactory;
	 @Resource
	private  ApplicationContext ctx ;
	 @Resource
	 private PlatformTransactionManager transactionManager;

    @Before
    public void rollBackBeforeHook() {
    	transactionManager=beanFactory.getBean(PlatformTransactionManager.class);
        txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    @After
    public void rollBackAfterHook() {
    	transactionManager.rollback(txStatus);
    }*/
}
