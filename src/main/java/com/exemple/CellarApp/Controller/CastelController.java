package com.exemple.CellarApp.Controller;

import com.exemple.CellarApp.Model.Bottle;
import com.exemple.CellarApp.Model.Castel;
import com.exemple.CellarApp.Service.Castel.CastelService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void deleteCastel(@PathVariable Integer id) {
        castelService.deleteOne(id);
    }

    @DeleteMapping()
    public void deleteCastels() {
        castelService.deleteAll();
    }

    @PutMapping("/{id}")
    public void modifyCastel(@PathVariable Integer id, @RequestBody Castel c) {
        castelService.modifyOne(id, c);
    }

    @PostMapping
    public void addCastel(@RequestBody Castel c) {
        castelService.addOne(c);
    }

}
