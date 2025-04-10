package com.quese.queseservice.configration;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages ="com.quese.queseservice.postgresql.dao",
transactionManagerRef ="postgresqlplatformTransactionManager",
entityManagerFactoryRef ="postgresqllocalContainerEntityManagerFactoryBean" )
public class PostgresqlDataConfigration {
	@Value("${spring.postgresql.jpa.properties.hibernate.dialect}")
	private String hibernateDialect;
	@Value("${spring.postgresql.jpa.hibernate.ddl-auto}")
	private String hibernateDdl;
	@Value("${spring.postgresql.jpa.generate-ddl}")
	private String hibernateShowSql;
	@Bean
	PlatformTransactionManager postgresqlplatformTransactionManager() {
		final JpaTransactionManager jpaTransactionManager=new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(postgresqllocalContainerEntityManagerFactoryBean().getObject());
		return jpaTransactionManager;
	}
	@Bean
	LocalContainerEntityManagerFactoryBean postgresqllocalContainerEntityManagerFactoryBean() {
		final LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean =new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(postgresqlDataSource());
		localContainerEntityManagerFactoryBean.setPackagesToScan("com.quese.queseservice.postgresql.entity");
		final HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
		final HashMap<String, Object> propertiesMap = new HashMap<>();
		propertiesMap.put("hibernate.dialect", hibernateDialect);
		propertiesMap.put("hibernate.hbm2ddl.auto", hibernateDdl);
		propertiesMap.put("hibernate.show_sql", hibernateShowSql);
		localContainerEntityManagerFactoryBean.setJpaPropertyMap(propertiesMap);
		return localContainerEntityManagerFactoryBean;
	}
	
	@ConfigurationProperties(prefix = "spring.postgresql.datasource")
	@Bean
	DataSource postgresqlDataSource() {
		return DataSourceBuilder.create().build();
	}
}
