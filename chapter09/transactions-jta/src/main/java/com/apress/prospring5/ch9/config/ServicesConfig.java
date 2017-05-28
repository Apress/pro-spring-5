package com.apress.prospring5.ch9.config;

import com.atomikos.icatch.config.UserTransactionService;
import com.atomikos.icatch.config.UserTransactionServiceImp;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.Properties;

/**
 * Created by iuliana.cosmina on 5/17/17.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.apress.prospring5.ch9.services")
public class ServicesConfig {

	private Logger logger = LoggerFactory.getLogger(ServicesConfig.class);


	@Bean(initMethod = "init", destroyMethod = "shutdownForce")
	public UserTransactionService userTransactionService(){
		Properties atProps = new Properties();
		atProps.put("com.atomikos.icatch.service", "com.atomikos.icatch.standalone.UserTransactionServiceFactory");
		return new UserTransactionServiceImp(atProps);
	}

	@Bean (initMethod = "init", destroyMethod = "close")
	@DependsOn("userTransactionService")
	public UserTransactionManager  atomikosTransactionManager(){
		UserTransactionManager utm = new UserTransactionManager();
		utm.setStartupTransactionService(false);
		utm.setForceShutdown(true);
		return utm;
	}

	@Bean
	@DependsOn("userTransactionService")
	public UserTransaction userTransaction(){
		UserTransactionImp ut = new UserTransactionImp();
		try {
			ut.setTransactionTimeout(300);
		} catch (SystemException se) {
			logger.error("Configuration  exception.", se);
			return null;
		}
		return ut;
	}

	@Bean
	public PlatformTransactionManager transactionManager(){
		JtaTransactionManager ptm = new JtaTransactionManager();
		ptm.setTransactionManager(atomikosTransactionManager());
		ptm.setUserTransaction(userTransaction());
		return ptm;
	}

}
