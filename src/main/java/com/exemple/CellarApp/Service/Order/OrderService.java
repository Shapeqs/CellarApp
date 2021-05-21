package com.exemple.CellarApp.Service.Order;


import com.exemple.CellarApp.DTO.OrderDTO;
import com.exemple.CellarApp.Model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    List<Order> findAll();

    Order findOne(Integer id);

    void deleteOne(Integer id);

    void deleteAll();

    void addOne(OrderDTO o);

    void modifyOne(Integer id, Order o);
}
