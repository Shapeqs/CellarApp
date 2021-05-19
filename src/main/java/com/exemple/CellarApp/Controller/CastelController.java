package com.exemple.CellarApp.Controller;

import com.exemple.CellarApp.Model.Castel;
import com.exemple.CellarApp.Service.Castel.CastelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/castels")
public class CastelController {

    @Autowired
    private CastelService castelService;

    @GetMapping
    public List<Castel> getAll() {
        return castelService.findAll();
    }

    @GetMapping("/{id}")
    public Castel getOne(@PathVariable Integer id) {
        return castelService.findOne(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void deleteCastel(@PathVariable Integer id) {
        castelService.deleteOne(id);
    }

    @DeleteMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void deleteCastels() {
        castelService.deleteAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void modifyCastel(@PathVariable Integer id, @RequestBody Castel c) {
        castelService.modifyOne(id, c);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void addCastel(@RequestBody Castel c) {
        castelService.addOne(c);
    }

}
