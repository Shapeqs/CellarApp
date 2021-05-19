package com.exemple.CellarApp.Security;

import com.exemple.CellarApp.Model.Employe;
import com.exemple.CellarApp.Service.Employe.EmployeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

import static com.exemple.CellarApp.EnumUtils.UserRoles.ADMIN;
import static com.exemple.CellarApp.EnumUtils.UserRoles.EMPLOYE;

@Configuration
@EnableWebSecurity()
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeService employeService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/bottles","/castels", "/naming", "/bottles/*", "/castels/*", "/naming/*" ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        List<Employe> all = employeService.findAll();
        List<UserDetails> list = new ArrayList<>();
        for (Employe e : all) {
            UserDetails userBuilded;
            if (e.getUsername().equals("cquerre") || e.getUsername().equals("dcalippe")) {
                userBuilded = User.builder()
                        .username(e.getUsername())
                        .password(passwordEncoder.encode(e.getPassword()))
                        .roles(ADMIN.name()) // ROLE_ADMIN
                        .build();
            } else {
                userBuilded = User.builder()
                        .username(e.getUsername())
                        .password(passwordEncoder.encode(e.getPassword()))
                        .roles(EMPLOYE.name()) // ROLE_ADMIN
                        .build();
            }
            list.add(userBuilded);
            LOGGER.info(String.format("User: %s (pass : %s) has been add to the app", userBuilded.getUsername(), userBuilded.getPassword()));
        }
        return new InMemoryUserDetailsManager(list);
    }
}
