package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Person, Integer> {

    //запрос на получение пользователя по логину
    List<Person> findByLogin(String login);

    List<Person> findByPassword(String password);

}
