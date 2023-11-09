package com.kirillalekseev.spring.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.jdbcAuthentication().dataSource(dataSource);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/").hasAnyRole("USER","ADMIN")
                .antMatchers("/book_info").hasAnyRole("USER","ADMIN")
                .antMatchers("/magazines_info").hasAnyRole("USER","ADMIN")
                .antMatchers("users_item_info").hasRole("USER")
                .antMatchers("Add-new-book").hasRole("ADMIN")
                .antMatchers("Add-new-magazine").hasRole("ADMIN")
                .antMatchers("/manager_info/**").hasRole("ADMIN")
                .and().formLogin()
                .loginPage("/login")// Указываем свою страницу входа
                .defaultSuccessUrl("/")// Перенаправление после успешной аутентификации
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}
