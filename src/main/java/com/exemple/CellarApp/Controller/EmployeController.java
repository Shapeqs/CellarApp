package com.exemple.CellarApp.Controller;

import com.exemple.CellarApp.Model.Employe;
import com.exemple.CellarApp.Service.Employe.EmployeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class EmployeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeController.class);

    @Autowired
    private EmployeService employeService;

    @GetMapping("/api/employes")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYE')")
    public List<Employe> getAll() {
        return employeService.findAll();
    }

    @RequestMapping("/api/login")
    public boolean login(@RequestBody Employe employe) {
        Employe oneByLogin = employeService.findOneByLogin(employe.getUsername());
        if (null != oneByLogin) {
            LOGGER.info("PassSend : " + employe.getPassword());
            LOGGER.info("PassOne : " + oneByLogin.getPassword());
            return employe.getPassword().equals(oneByLogin.getPassword());
        } else {
            LOGGER.error(String.format("Username %s not found ! ", employe.getUsername()));
            return false;
        }
    }

    @RequestMapping("/api/user")
    public Principal user(Principal user) {
        return user;
    }
}