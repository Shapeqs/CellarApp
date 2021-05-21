package com.exemple.CellarApp.Controller;


import com.exemple.CellarApp.DTO.OrderDTO;
import com.exemple.CellarApp.Model.Order;
import com.exemple.CellarApp.Service.Order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order getOne(@PathVariable Integer id) {
        return orderService.findOne(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteOne(id);
    }

    @DeleteMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void deleteOrders() {
        orderService.deleteAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void modifyOrder(@PathVariable Integer id, @RequestBody Order o) {
        orderService.modifyOne(id, o);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public void addOrder(@RequestBody OrderDTO o) {
        orderService.addOne(o);
    }
}
