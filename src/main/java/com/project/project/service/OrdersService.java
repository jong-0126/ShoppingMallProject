package com.project.project.service;

import com.project.project.domain.entity.Orders;
import com.project.project.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    public List<Orders> ordersList(){

        return ordersRepository.findAll();
    }
}
