package com.exemple.CellarApp.Controller;

import com.exemple.CellarApp.Model.Employe;
import com.exemple.CellarApp.Service.Employe.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employes")
public class LoginController {

    @Autowired
    EmployeService employeService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public List<Employe> getAll() {
        return employeService.findAll();
    }
}
