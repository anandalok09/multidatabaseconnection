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
@EnableJpaRepositories(basePackages ="com.quese.queseservice.mysql.dao",
transactionManagerRef ="mysqlplatformTransactionManager",
entityManagerFactoryRef ="mysqllocalContainerEntityManagerFactoryBean" )
public class MySqlDataConfigration {
	@Value("${spring.mysql.jpa.properties.hibernate.dialect}")
	private String hibernateDialect;
	@Value("${spring.mysql.jpa.hibernate.ddl-auto}")
	private String hibernateDdl;
	@Value("${spring.mysql.jpa.generate-ddl}")
	private String hibernateShowSql;
	@Bean
	PlatformTransactionManager mysqlplatformTransactionManager() {
		final JpaTransactionManager jpaTransactionManager=new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(mysqllocalContainerEntityManagerFactoryBean().getObject());
		return jpaTransactionManager;
	}
	@Bean
	LocalContainerEntityManagerFactoryBean mysqllocalContainerEntityManagerFactoryBean() {
		final LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean =new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(mysqlDataSource());
		localContainerEntityManagerFactoryBean.setPackagesToScan("com.quese.queseservice.mysql.entity");
		final HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
		final HashMap<String, Object> propertiesMap = new HashMap<>();
		propertiesMap.put("hibernate.dialect", hibernateDialect);
		propertiesMap.put("hibernate.hbm2ddl.auto", hibernateDdl);
		propertiesMap.put("hibernate.show_sql", hibernateShowSql);
		localContainerEntityManagerFactoryBean.setJpaPropertyMap(propertiesMap);
		return localContainerEntityManagerFactoryBean;
	}
	
	@ConfigurationProperties(prefix = "spring.mysql.datasource")
	@Bean
	DataSource mysqlDataSource() {
		return DataSourceBuilder.create().build();
	}
}
