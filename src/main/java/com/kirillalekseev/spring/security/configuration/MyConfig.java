package com.kirillalekseev.spring.security.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;



import java.beans.PropertyVetoException;

import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.kirillalekseev.spring.security")
@EnableTransactionManagement
public class MyConfig implements WebMvcConfigurer {



    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public ComboPooledDataSource dataSource(){
        ComboPooledDataSource dataSource
                = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("org.postgresql.Driver");
            dataSource.setJdbcUrl("jdbc:postgresql://127.0.0.1:5432/postgres?useSSL=false&serverTimezone=UTC");
            dataSource.setUser("postgres");
            dataSource.setPassword("1111");
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean  sessionFacory =
                new LocalSessionFactoryBean();

        sessionFacory.setDataSource(dataSource());
        sessionFacory.setPackagesToScan("com.kirillalekseev.spring.security.entity");

        Properties hibernateProperies = new Properties();

        hibernateProperies.setProperty("hibernate.dialect"
                ,"org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperies.setProperty("hibernate.show_sql"
                ,"true");

        sessionFacory.setHibernateProperties(hibernateProperies);

        return sessionFacory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();

        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
