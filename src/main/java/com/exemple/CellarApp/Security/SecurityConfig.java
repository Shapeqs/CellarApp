package com.exemple.CellarApp.Security;

import com.exemple.CellarApp.Model.User;
import com.exemple.CellarApp.Service.User.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity()
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/",
                        "/api/login/",
                        "/api/bottles/",
                        "/api/images/*",
                        "/api/castels/",
                        "/api/namings/",
                        "/api/bottles/*",
                        "/api/castels/*",
                        "/naming/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        List<User> all = userService.findAll();
        List<UserDetails> list = new ArrayList<>();
        for (User e : all) {
            UserDetails userBuilded;
                userBuilded = org.springframework.security.core.userdetails.User.builder()
                        .username(e.getUsername())
                        .password(passwordEncoder.encode(e.getPassword()))
                        .roles(e.getRole().name())
                        .build();
            list.add(userBuilded);
            LOGGER.info(String.format("%s : %s (pass : %s) has been add to the app", e.getRole().name(), userBuilded.getUsername(), e.getPassword()));
        }
        return new InMemoryUserDetailsManager(list);
    }
}
