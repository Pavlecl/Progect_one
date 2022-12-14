package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByPerson(Person person);

    //запрос на получение пользователей по фамилии, где первые буквы начинаются с определенной последовательности
    List<Order> findByNumberAfter(String starting_with);

    @Query(value = "select * from orders where orders.number ='%1?' ", nativeQuery = true)
    List<Order> findByLastnameOrder(String last_name);
}
