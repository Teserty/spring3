package com.teserty.spring3.configs;

import com.teserty.spring3.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public WebSecurityConfig(BCryptPasswordEncoder passwordEncoder, UserServiceImp userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserServiceImp userService;
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
        auth
                .inMemoryAuthentication()
                .withUser("user").password(passwordEncoder.encode("{noop}password")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder.encode("{noop}admin")).roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/login*").permitAll()
                //Доступ только для не зарегистрированных пользователей
                .antMatchers("/users/**").permitAll()
                //Доступ только для пользователей с ролью Администратор
                .antMatchers("/admin/**").hasRole("ADMIN")
                //Доступ разрешен всем пользователей

                .antMatchers("/shops/**","/items/**", "/resources/**").permitAll()
                .antMatchers("/items/new/item").hasRole("ADMIN")
                //Все остальные страницы требуют аутентификации
                //.anyRequest().authenticated()
                .and().httpBasic()
                .and().sessionManagement().disable()
                .rememberMe()
                    .alwaysRemember(true)
                    .tokenValiditySeconds(60*60*24)
                    .rememberMeCookieName("mouni")
                    .key("somesecret")
                .and()
                .formLogin().disable()
                .csrf().disable();
    }
}