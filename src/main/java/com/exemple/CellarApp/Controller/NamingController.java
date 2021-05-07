package com.exemple.CellarApp.Controller;

import com.exemple.CellarApp.Model.Castel;
import com.exemple.CellarApp.Model.Naming;
import com.exemple.CellarApp.Service.Naming.NamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/namings")
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

    @DeleteMapping("/{id}")
    public void deleteCastel(@PathVariable Integer id) {
        namingService.deleteOne(id);
    }

    @DeleteMapping()
    public void deleteCastels() {
        namingService.deleteAll();
    }

    @PutMapping("/{id}")
    public void modifyCastel(@PathVariable Integer id, @RequestBody Naming n) {
        namingService.modifyOne(id, n);
    }

    @PostMapping
    public void addCastel(@RequestBody Naming n) {
        namingService.addOne(n);
    }
}
