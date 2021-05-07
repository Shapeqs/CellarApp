package com.exemple.CellarApp.Controller;

import com.exemple.CellarApp.Model.Bottle;
import com.exemple.CellarApp.Service.Bottle.BottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bottles")
public class BottleController {

    @Autowired
    private BottleService bottleService;

    @GetMapping
    public List<Bottle> getAll() {
        return bottleService.findAll();
    }

    @GetMapping("/{id}")
    public Bottle getOne(@PathVariable Integer id) {
        return bottleService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBottle(@PathVariable Integer id) {
        bottleService.deleteOne(id);
    }

    @DeleteMapping()
    public void deleteBottles() {
        bottleService.deleteAll();
    }

    @PutMapping("/{id}")
    public void modifyBottle(@PathVariable Integer id, @RequestBody Bottle b) {
        bottleService.modifyOne(id, b);
    }

    @PostMapping
    public void addBottle(@RequestBody Bottle b) {
        bottleService.addOne(b);
    }

}
