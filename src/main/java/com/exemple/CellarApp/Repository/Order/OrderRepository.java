package com.exemple.CellarApp.Repository.Order;


import com.exemple.CellarApp.DTO.OrderDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {
    List<OrderDTO> findAll();

    OrderDTO findById(Integer id);

    void addNew(OrderDTO orderDTO);

    void modify(Integer id, OrderDTO orderDTO);

    void deleteById(Integer id);

    void deleteAll();
}
