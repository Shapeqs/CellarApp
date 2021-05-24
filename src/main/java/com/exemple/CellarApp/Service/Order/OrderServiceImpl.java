package com.exemple.CellarApp.Service.Order;


import com.exemple.CellarApp.DTO.EntryBottleDTO;
import com.exemple.CellarApp.DTO.OrderDTO;
import com.exemple.CellarApp.Model.Bottle;
import com.exemple.CellarApp.Model.Order;
import com.exemple.CellarApp.Repository.Order.OrderRepository;
import com.exemple.CellarApp.Service.Bottle.BottleService;
import com.exemple.CellarApp.Service.Client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    BottleService bottleService;

    @Override
    public List<Order> findAll() {
        ArrayList<Order> orders = new ArrayList<>();
        for (OrderDTO o : orderRepository.findAll()) {
            orders.add(transformDTOtoEntity(o));
        }
        return orders;
    }

    @Override
    public Order findOne(Integer id) {
        return transformDTOtoEntity(orderRepository.findById(id));
    }

    @Override
    public void deleteOne(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        orderRepository.deleteAll();
    }

    @Override
    public void addOne(Order o) {
        orderRepository.addNew(transformEntitytoDTO(o));
    }

    @Override
    public void modifyOne(Integer id, Order o) {
        orderRepository.modify(id, transformEntitytoDTO(o));
    }

    private Order transformDTOtoEntity(OrderDTO orderDTO) {
        Order o = new Order();
        o.setId(orderDTO.getId());
        o.setOrderDate(orderDTO.getOrderDate());
        o.setClient(clientService.findOne(orderDTO.getIdClient()));
        HashMap<Bottle, Integer> listBottles = new HashMap<>();
        for (EntryBottleDTO entry : orderDTO.getListBottles()) {
            listBottles.put(bottleService.findOne(entry.getIdBottle()), entry.getQuantity());
        }
        o.setListBottles(listBottles);
        return o;
    }

    private OrderDTO transformEntitytoDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setIdClient(order.getClient().getId());
        List<EntryBottleDTO> listBottles = new ArrayList<>();
        order.getListBottles().forEach((bottle, quantity) ->
                listBottles.add(new EntryBottleDTO(bottle.getId(), quantity))
        );
        orderDTO.setListBottles(listBottles);
        return orderDTO;
    }
}
