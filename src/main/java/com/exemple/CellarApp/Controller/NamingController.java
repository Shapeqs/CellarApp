package com.exemple.CellarApp.Controller;

import com.exemple.CellarApp.Model.Naming;
import com.exemple.CellarApp.Service.Naming.NamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/namings")
public class NamingController {

    @Autowired
    private NamingService namingService;

    @GetMapping
    public List<Naming> getAll() {
        return namingService.findAll();
    }

    @GetMapping("/{id}")
    public Naming getOne(@PathVariable Integer id) {
        return namingService.findOne(id);
    }

    @GetMapping("/find/{name}")
    public Naming findByName(@PathVariable String name) {
        return namingService.findByName(name);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void deleteCastel(@PathVariable Integer id) {
        namingService.deleteOne(id);
    }

    @DeleteMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void deleteCastels() {
        namingService.deleteAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void modifyCastel(@PathVariable Integer id, @RequestBody Naming n) {
        namingService.modifyOne(id, n);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void addCastel(@RequestBody Naming n) {
        namingService.addOne(n);
    }
}
