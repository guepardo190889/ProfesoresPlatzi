package com.blackdeath.profesoresplatzi.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Seth Luis
 * 
 *         Sobre @Bean y @Autowired:
 *         https://stackoverflow.com/questions/34172888/difference-between-bean-and-autowired
 * 
 *         Arreglar problema de
 *         org.springframework.beans.factory.UnsatisfiedDependencyException:
 *         Error creating bean with name 'jpaContext': Unsatisfied dependency
 *         expressed through constructor parameter 0; nested exception is
 *         org.springframework.beans.factory.NoSuchBeanDefinitionException: No
 *         qualifying bean of type
 *         'java.util.Set<javax.persistence.EntityManager>' available: expected
 *         at least 1 bean which qualifies as autowire candidate. Dependency
 *         annotations: {}
 *         https://stackoverflow.com/questions/49078470/spring-error-creating-bean-with-name-jpacontext/49078669#49078669
 *
 */
@Configuration
@EnableTransactionManagement
public class DataBaseConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(getDatSource());
		em.setPackagesToScan("com.blackdeath.profesoresplatzi.model");
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return em;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(gtSessionFactory().getObject());

		return hibernateTransactionManager;
	}

	@Bean
	@Autowired
	public LocalSessionFactoryBean gtSessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(getDatSource());
		sessionFactoryBean.setPackagesToScan("com.blackdeath.profesoresplatzi.model");
		sessionFactoryBean.setHibernateProperties(getHibernateProperties());

		return sessionFactoryBean;
	}

	@Bean
	public DataSource getDatSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		// autoReconnect=true&useSSL=false se agrega para evitar error con el SSL
		dataSource.setUrl("jdbc:mysql://localhost:3306/platziprofesores?autoReconnect=true&useSSL=false");
		dataSource.setUsername("platziprofesores");
		dataSource.setPassword("platziprofesores");

		return dataSource;
	}

	public Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("show_sql", "true");

		return properties;
	}

}
