package com.exemple.CellarApp.Repository.Order;


import com.exemple.CellarApp.DTO.OrderDTO;
import com.exemple.CellarApp.EnumUtils.URLs;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    /**
     * Le logger de la classe
     */
    private Logger LOGGER = LoggerFactory.getLogger(OrderRepositoryImpl.class);
    /**
     * Le mapper pour transformer les entités en json
     */
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<OrderDTO> findAll() {
        List<OrderDTO> listOrder = new ArrayList<>();
        try {
            listOrder = mapper.readValue(new URL("file:" + URLs.Order.url), new TypeReference<>() {
            });
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return listOrder;
    }

    @Override
    public OrderDTO findById(Integer id) {
        return findAll()
                .stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addNew(OrderDTO orderDTO) {
        List<OrderDTO> listOrder = findAll();
        if (!listOrder.isEmpty()) {
            orderDTO.setId(listOrder.get(listOrder.size() - 1).getId() + 1);
        } else {
            orderDTO.setId(0);
        }
        listOrder.add(orderDTO);
        try {
            mapper.writeValue(new File(URLs.Order.url), listOrder);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void modify(Integer id, OrderDTO orderDTO) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void deleteAll() {

    }
}
