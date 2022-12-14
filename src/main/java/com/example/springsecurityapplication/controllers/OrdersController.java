package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.OrderRepository;
import com.example.springsecurityapplication.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class OrdersController {

    private final OrderRepository orderRepository;

    private final OrderService orderService;

    @Autowired
    public OrdersController(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

//    @GetMapping("/userOrders")
//    public String ordersUser(Model model){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
//        List<Order> orderList = orderRepository.findByPerson(personDetails.getPerson());
//        model.addAttribute("orders", orderList);
//        return "/admin/userOrders";
//    }

    @GetMapping("/userOrders")
    public String ordersUser(Model model){
//        model.addAttribute("user", userDAO.getUsers());
        model.addAttribute("orders", orderService.getAllOrder());
        return "admin/userOrders";
    }

    //метод по удалению заказ
    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable("id") int id){
        orderService.deleteOrder(id);
        return "redirect:/admin";
    }

//    //метод возвращает страницу с формой роедактирования заказа
//    @GetMapping("/editOrder/{id}")
//    public String getFormEditOrder(@PathVariable("id") int id, Model model){
//        model.addAttribute("editOrder", orderService.getOrderById(id));
//        return "admin/editUser";
//    }

    // Метод по получению товара по id и отображение шаблона редактирования
    @GetMapping("/editOrder/{id}")
    public String getFormEditOrder(@PathVariable("id") int id, Model model){
        model.addAttribute("editOrder", orderService.getOrderById(id));
//        model.addAttribute("category", categoryRepository.findAll());
        return "admin/editOrders";
    }

    @PostMapping("/editOrder/{id}")
    public String editOrder(@ModelAttribute("editOrder")Order order, @PathVariable("id") int id){
        orderService.updateOrder(id, order);
        return "admin/editOrders";
    }

//    @GetMapping("/admin/searching")
//    public String sorting_and_searching(){
//        return "admin/searching";
//    }

//    @PostMapping("/sorting_and_searсhing")
//    public String sorting_and_searching(@RequestParam ("SortingAndSearching") String sortingAndSearching, @RequestParam("value") String value, Model model){
//
//            model.addAttribute("oredr", orderService.getOrderStartingWith(value));
//        return "admin/searching";
//        }

    @PostMapping("/searching")
    public String orderSearch(@RequestParam("search") String search, String category, Model model){

            model.addAttribute("search_product",orderRepository.findByNumberAfter(search));


        return "/admin/userOrders";
    }

}
