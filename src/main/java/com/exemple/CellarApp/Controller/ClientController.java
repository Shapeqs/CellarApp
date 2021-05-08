package com.exemple.CellarApp.Controller;

import com.exemple.CellarApp.Model.Client;
import com.exemple.CellarApp.Service.Client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAll() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public Client getOne(@PathVariable Integer id) {
        return clientService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBottle(@PathVariable Integer id) {
        clientService.deleteOne(id);
    }

    @DeleteMapping()
    public void deleteBottles() {
        clientService.deleteAll();
    }

    @PutMapping("/{id}")
    public void modifyBottle(@PathVariable Integer id, @RequestBody Client c) {
        clientService.modifyOne(id, c);
    }

    @PostMapping
    public void addBottle(@RequestBody Client c) {
        clientService.addOne(c);
    }


}
