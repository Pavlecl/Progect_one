package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //данный метод позволяет получить все заказы
    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    //данный метод позволяет получить заказы по id
    public Order getOrderById(int id){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        // если в Optional находится объект модели то его возвращаем, иначе возвращаем null
        return optionalOrder.orElse(null);
    }

    //данный метод позволяет сохранить заказ
    @Transactional
    public void saveOrder(Order order){
        orderRepository.save(order);
    }

    //данный метод позволяет обновить заказ
    @Transactional
    public void updateOrder(int id, Order order){
        order.setId(id);
        orderRepository.save(order);
    }

    //данный метод позволяет удалить заказ по id
    @Transactional
    public void deleteOrder(int id){
        orderRepository.deleteById(id);
    }

    //данный метод позволяет получить пользователя по фамилии и отсортировать по возрасту
//    public List<Order> getOrderStartingWith(String starting_with){
//        List<Order> orders = orderRepository.getReferenceById(starting_with);
//        return orders;
//    }

}
