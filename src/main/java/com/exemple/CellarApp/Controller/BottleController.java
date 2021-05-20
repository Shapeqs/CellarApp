package com.exemple.CellarApp.Controller;

import com.exemple.CellarApp.Model.Bottle;
import com.exemple.CellarApp.Service.Bottle.BottleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bottles")
public class BottleController {

    private final Logger LOGGER = LoggerFactory.getLogger(BottleController.class);

    @Autowired
    private BottleService bottleService;

    @GetMapping
    public List<Bottle> getAll() {
        LOGGER.info("TOUCHAGE DE CUL");
        return bottleService.findAll();
    }

    @GetMapping("/{id}")
    public Bottle getOne(@PathVariable Integer id) {
        return bottleService.findOne(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void deleteBottle(@PathVariable Integer id) {
        bottleService.deleteOne(id);
    }

    @DeleteMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void deleteBottles() {
        bottleService.deleteAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void modifyBottle(@PathVariable Integer id, @RequestBody Bottle b) {
        bottleService.modifyOne(id, b);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void addBottle(@RequestBody Bottle b) {
        bottleService.addOne(b);
    }

}
