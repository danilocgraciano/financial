package br.com.financial.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
	public DataSource mainDataSource(Environment env) {

		String userName = System.getProperty("RDS_USERNAME");
		String password = System.getProperty("RDS_PASSWORD");
		String hostname = System.getProperty("RDS_HOSTNAME");
		String port = System.getProperty("RDS_PORT");

		if (hostname == null || hostname.trim().isEmpty())
			hostname = "localhost";

		if (port == null || port.trim().isEmpty())
			port = "5432";

		if (userName == null || userName.trim().isEmpty())
			userName = "postgres";

		if (password == null || password.trim().isEmpty())
			password = "postgres";

		String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/financial";

		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl(jdbcUrl);
		ds.setUsername(userName);
		ds.setPassword(password);
		ds.setValidationQuery("select current_date");
		ds.setRemoveAbandoned(true);
		ds.setRemoveAbandonedTimeout(30);
		ds.setLogAbandoned(true);

		return ds;
	}
	
//	@Bean(destroyMethod = "close")
//	DataSource dataSource(Environment env) {
//		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
//		dsLookup.setResourceRef(true);
//		DataSource dataSource = dsLookup.getDataSource("jdbc/financial");
//		return dataSource;
//	}
	
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
