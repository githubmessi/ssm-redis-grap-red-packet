package com.ssm.chapter20.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(value = "com.ssm.chapter20.service",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Service.class})})
//@PropertySource(value = "classpath:db.properties")
@EnableTransactionManagement
public class RootConfig implements TransactionManagementConfigurer {

    private DataSource dataSource;
   /* @Value("jdbc.driver")
    private String driverClassName;

    @Value("jdbc.url")
    private String url;

    @Value("jdbc.username")
    private String username;

    @Value("jdbc.password")
    private String password;

    private DataSource dataSource;

    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(200);
        dataSource.setMaxWait(30000);
        return dataSource;
    }*/

   @Bean
   public DataSource dataSource(){
       if(dataSource != null){
           return dataSource;
       }
       Properties properties = new Properties();
       properties.setProperty("driverClassName", "com.mysql.jdbc.Driver");
       properties.setProperty("url", "jdbc:mysql://localhost:3306/springmvc?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
       properties.setProperty("username", "root");
       properties.setProperty("password", "123");
       properties.setProperty("maxActive", "200");
       properties.setProperty("maxIdle", "20");
       properties.setProperty("maxWait","30000");
       try {
           dataSource = BasicDataSourceFactory.createDataSource(properties);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return dataSource;
   }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        Resource resource = new ClassPathResource("mybatis/SqlMapConfig.xml");
        sqlSessionFactoryBean.setConfigLocation(resource);
        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("com.ssm.chapter20.mapper");
//        msc.setAnnotationClass(Repository.class);
        return msc;
    }

    @Override
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }
}
