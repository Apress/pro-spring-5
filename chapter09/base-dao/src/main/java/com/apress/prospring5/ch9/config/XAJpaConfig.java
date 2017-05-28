package com.apress.prospring5.ch9.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Properties;

/**
 * Created by iuliana.cosmina on 4/29/17.
 */
@Configuration
@EnableJpaRepositories
public class XAJpaConfig {

	private static Logger logger = LoggerFactory.getLogger(XAJpaConfig.class);

	@SuppressWarnings("unchecked")
	@Bean(initMethod = "init", destroyMethod = "close")
	public DataSource dataSourceA() {
		try {
			AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
			dataSource.setUniqueResourceName("XADBMSA");
			dataSource.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
			dataSource.setXaProperties(xaAProperties());
			dataSource.setPoolSize(1);
			return dataSource;
		} catch (Exception e) {
			logger.error("Populator DataSource bean cannot be created!", e);
			return null;
		}
	}

	@Bean
	public Properties xaAProperties() {
		Properties xaProp = new Properties();
		xaProp.put("databaseName", "musicdb_a");
		xaProp.put("user", "prospring5_a");
		xaProp.put("password", "prospring5_a");
		return xaProp;
	}

	@SuppressWarnings("unchecked")
	@Bean(initMethod = "init", destroyMethod = "close")
	public DataSource dataSourceB() {
		try {
			AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
			dataSource.setUniqueResourceName("XADBMSB");
			dataSource.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
			dataSource.setXaProperties(xaBProperties());
			dataSource.setPoolSize(1);
			return dataSource;
		} catch (Exception e) {
			logger.error("Populator DataSource bean cannot be created!", e);
			return null;
		}
	}

	@Bean
	public Properties xaBProperties() {
		Properties xaProp = new Properties();
		xaProp.put("databaseName", "musicdb_b");
		xaProp.put("user", "prospring5_b");
		xaProp.put("password", "prospring5_b");
		return xaProp;
	}


	@Bean
	public Properties hibernateProperties() {
		Properties hibernateProp = new Properties();
		hibernateProp.put("hibernate.transaction.factory_class", "org.hibernate.transaction.JTATransactionFactory");
		hibernateProp.put("hibernate.transaction.jta.platform", "com.atomikos.icatch.jta.hibernate4.AtomikosPlatform");
		// required by Hibernate 5
		hibernateProp.put("hibernate.transaction.coordinator_class", "jta");
		hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		// this will work only if users/schemas are created first, use ddl.sql script for this
		hibernateProp.put("hibernate.hbm2ddl.auto", "update");
		hibernateProp.put("hibernate.show_sql", true);
		hibernateProp.put("hibernate.max_fetch_depth", 3);
		hibernateProp.put("hibernate.jdbc.batch_size", 10);
		hibernateProp.put("hibernate.jdbc.fetch_size", 50);
		return hibernateProp;
	}


	@Bean
	public EntityManagerFactory emfA() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPackagesToScan("com.apress.prospring5.ch9.entities");
		factoryBean.setDataSource(dataSourceA());
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factoryBean.setJpaProperties(hibernateProperties());
		factoryBean.setPersistenceUnitName("emfA");
		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}


	@Bean
	public EntityManagerFactory emfB() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPackagesToScan("com.apress.prospring5.ch9.entities");
		factoryBean.setDataSource(dataSourceB());
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factoryBean.setJpaProperties(hibernateProperties());
		factoryBean.setPersistenceUnitName("emfB");
		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}
}
