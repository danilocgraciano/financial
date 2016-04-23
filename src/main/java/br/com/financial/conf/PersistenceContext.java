package br.com.financial.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = { "br.com.financial" })
@EnableTransactionManagement
/**
 * 
 * See {@link http://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-one-configuration/}
 * */
public class PersistenceContext {

//	The method that configures the datasource bean looks as follows:
	
	@Bean(destroyMethod = "close")
	DataSource dataSource(Environment env) {
		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		dsLookup.setResourceRef(true);
		DataSource dataSource = dsLookup.getDataSource("jdbc/financial");
		return dataSource;
	}
	
//	The method that configures the entity manager factory bean looks as follows:
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("br.com.financial");

		Properties jpaProperties = new Properties();
		
		jpaProperties.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
//		jpaProperties.put("hibernate.hbm2ddl.auto","update");
		jpaProperties.put("hibernate.hbm2ddl.auto","create-drop");
		jpaProperties.put("hibernate.ejb.naming_strategy","org.hibernate.cfg.ImprovedNamingStrategy");
//		jpaProperties.put("hibernate.show_sql","true");
//		jpaProperties.put("hibernate.format_sql","true");
		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}

//	The method that configures the transaction manager bean looks as follows:
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

}
