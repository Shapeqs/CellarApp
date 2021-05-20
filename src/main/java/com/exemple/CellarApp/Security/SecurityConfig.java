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

import static com.exemple.CellarApp.Security.UserRoles.ADMIN;
import static com.exemple.CellarApp.Security.UserRoles.EMPLOYE;

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
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/",
                        "/api/login/",
                        "/api/user/",
                        "/api/bottles/",
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
        List<Employe> all = employeService.findAll();
        List<UserDetails> list = new ArrayList<>();
        for (Employe e : all) {
            UserDetails userBuilded;
            String role;
            if (e.getName().equals("Querre") || e.getName().equals("Calippe")) {
                userBuilded = User.builder()
                        .username(e.getUsername())
                        .password(passwordEncoder.encode(e.getPassword()))
                        .roles(ADMIN.name()) // ROLE_ADMIN
                        .build();
                role = ADMIN.name();
            } else {
                userBuilded = User.builder()
                        .username(e.getUsername())
                        .password(passwordEncoder.encode(e.getPassword()))
                        .roles(EMPLOYE.name()) // ROLE_ADMIN
                        .build();
                role = EMPLOYE.name();
            }
            list.add(userBuilded);
            LOGGER.info(String.format("%s : %s (pass : %s) has been add to the app", role, userBuilded.getUsername(), userBuilded.getPassword()));
        }
        return new InMemoryUserDetailsManager(list);
    }
}
