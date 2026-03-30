package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.Order;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public String viewOrderHistory(Model model, Principal principal) {
        if (principal == null) return "redirect:/login";

        Account account = accountRepository.findByLoginName(principal.getName()).orElse(null);
        if (account == null) return "redirect:/login";

        List<Order> orders = orderRepository.findByAccountIdOrderByOrderDateDesc(account.getId());
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/{id}")
    public String viewOrderDetails(@PathVariable("id") int id, Model model, Principal principal) {
        if (principal == null) return "redirect:/login";

        Order order = orderRepository.findById(id).orElse(null);
        if (order == null || order.getAccount() == null || !order.getAccount().getLogin_name().equals(principal.getName())) {
            return "redirect:/orders";
        }

        model.addAttribute("order", order);
        return "order-details";
    }
}
