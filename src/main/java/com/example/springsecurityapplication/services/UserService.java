package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //данный метод позволяет получить всех пользователей
    public List<Person> getAllUser(){
        return userRepository.findAll();
    }

    //данный метод позволяет получить пользователя по id
    public Person getUserById(int id){
        Optional<Person> optionalUser = userRepository.findById(id);
        // если в Optional находится объект модели то его возвращаем, иначе возвращаем null
        return optionalUser.orElse(null);
    }

    //данный метод позволяет сохранить пользователя
    @Transactional
    public void saveUser(Person person){
        userRepository.save(person);
    }

    //данный метод позволяет обновить данные пользователя
    @Transactional
    public void updateUser(int id, Person person){
        person.setId(id);
        userRepository.save(person);
    }

    //данный метод позволяет удалить пользователя по id
    @Transactional
    public void deleteUser(int id){
        userRepository.deleteById(id);
    }



}
