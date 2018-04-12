package com.dn.jta.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = "com.dn.jta.dao.db1", sqlSessionFactoryRef = "db1SqlSessionFactory")
public class DB1Config {

	@Bean(name = "db1DataSource")
	@Autowired
	public DataSource getDataSource(Environment env) {
		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
		String prefix = "spring.datasource.db1.";
		ds.setXaDataSourceClassName(env.getProperty(prefix + "type"));
		ds.setXaProperties(this.build(env, prefix));
		return ds;
	}

	@Bean(name = "db1SqlSessionFactory")
	@Primary
	public SqlSessionFactory setSqlSessionFactory(
			@Qualifier("db1DataSource") DataSource dataSource)
					throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath:mapping/db1/*.xml"));
		return bean.getObject();
	}

	private Properties build(Environment env, String prefix) {

		Properties prop = new Properties();
		prop.put("url", env.getProperty(prefix + "url"));
		prop.put("username", env.getProperty(prefix + "username"));
		prop.put("password", env.getProperty(prefix + "password"));
		prop.put("driverClassName",
				env.getProperty(prefix + "driverClassName", ""));

		return prop;
	}
}
