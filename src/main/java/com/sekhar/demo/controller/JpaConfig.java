package com.rbtsb.config;

import com.rbtsb.repositories.impl.BaseRepositoryImpl;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.rbtsb.repositories", repositoryBaseClass = BaseRepositoryImpl.class)
@ConfigurationProperties("oracle")
public class JpaConfig implements TransactionManagementConfigurer {

	@Value("${dataSource.driverClassName}")
	private String driver;
	@Value("${dataSource.url}")
	private String url;
	@Value("${dataSource.username}")
	private String username;
	@Value("${dataSource.password}")
	private String password;
	@Value("${hibernate.dialect}")
	private String dialect;
	@Value("${hibernate.hbm2ddl.auto}")
	private String hbm2ddlAuto;

	@Bean
	public DataSource configureDataSource() {
//		 HikariConfig config = new HikariConfig();
//		
//		 config.setDriverClassName(driver);
//		 config.setJdbcUrl(url);
//		 config.setUsername(username);
//		 config.setPassword(password);
//		 config.addDataSourceProperty("cachePrepStmts", "true");
//		 config.addDataSourceProperty("prepStmtCacheSize", "250");
//		 config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//		 config.addDataSourceProperty("useServerPrepStmts", "true");
//		
//		 return new HikariDataSource(config);
		OracleDataSource dataSource = null;
		try {
			dataSource = new OracleDataSource();
			dataSource.setUser(username);
			dataSource.setPassword(password);
			dataSource.setURL(url);
			dataSource.setImplicitCachingEnabled(true);
			dataSource.setFastConnectionFailoverEnabled(true);
		} catch (SQLException e) {

		}
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(configureDataSource());
		entityManagerFactoryBean.setPackagesToScan("com.rbtsb");
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Properties jpaProperties = new Properties();
//		jpaProperties.put(org.hibernate.cfg.Environment.SHOW_SQL, true);
		jpaProperties.put(org.hibernate.cfg.Environment.DIALECT, dialect);
		jpaProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, hbm2ddlAuto);
		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
}
