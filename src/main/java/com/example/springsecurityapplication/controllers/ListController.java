package com.example.springsecurityapplication.controllers;


import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.repositories.PersonRepository;
import com.example.springsecurityapplication.repositories.UserRepository;
import com.example.springsecurityapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class ListController {

    private final UserService userService;


    private final UserRepository userRepository;

    @Autowired
    public ListController(UserService userService, PersonRepository personRepository, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    //метод возвращает страницу с выводом пользователей и кладет объект пользователя в модель
    @GetMapping("/listUser")
    public String index(Model model){
//        model.addAttribute("user", userDAO.getUsers());
        model.addAttribute("user", userService.getAllUser());
        return "admin/listUser";
    }

    //метод возвращает страницу с подробной информацией о пользователе
    @GetMapping("/info/{id}")
    public String infoUser(@PathVariable("id") int id, Model model){
//        model.addAttribute("user", userDAO.getUserId(id));
        model.addAttribute("user", userService.getUserById(id));
        return "admin/userInfo";
    }

    //метод возвращает страницу с формой роедактирования пользователя и помещает в модель объект редактируемого пользователья по id
    @GetMapping("/edit/{id}")
    public String getFormEditUser(@PathVariable("id") int id, Model model){
//        model.addAttribute("editUser", userDAO.getUserId(id));
        model.addAttribute("editUser", userService.getUserById(id));
        model.addAttribute("role", userRepository.findAll());
        return "admin/editUser";
    }

    //метод принимает объект с формы и обновляет пользователя
//    @PostMapping("/edit/{id}")
//    public String editUser(@ModelAttribute("editUser") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id){
//        if (bindingResult.hasErrors())
//        {
//            return "admin/editUser";
//        }
////        userDAO.updateUser(id, user);
//        userService.updateUser(id, person);
//        return "redirect:/admin";
//    }

    @PostMapping("/edit/{id}")
    public String editUser(@ModelAttribute("editUser") Person person, @PathVariable("id") int id){
        userService.updateUser(id, person);
        return "redirect:/admin";
    }

    //метод по удалению пользователей
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
//        userDAO.deleteUser(id);
        userService.deleteUser(id);
        return "redirect:/admin";
    }


}
