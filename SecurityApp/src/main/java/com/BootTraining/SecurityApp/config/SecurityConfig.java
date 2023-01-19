package com.BootTraining.SecurityApp.config;

import com.BootTraining.SecurityApp.security.AuthProviderImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProviderImpl1 authProviderImpl1;

    @Autowired
    public SecurityConfig(AuthProviderImpl1 authProviderImpl1) {
        this.authProviderImpl1 = authProviderImpl1;
    }


    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authProviderImpl1);
    }
}
