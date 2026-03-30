package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/checkout")
    public String checkoutForm(Model model, Principal principal) {
        if (cartService.getItems().isEmpty()) {
            return "redirect:/cart";
        }
        model.addAttribute("order", new Order());
        model.addAttribute("totalAmount", cartService.getAmount());
        return "checkout";
    }

    @PostMapping("/checkout")
    public String processCheckout(@ModelAttribute("order") Order order, Principal principal) {
        if (cartService.getItems().isEmpty()) {
            return "redirect:/cart";
        }
        
        if (principal != null) {
            Account account = accountRepository.findByLoginName(principal.getName()).orElse(null);
            order.setAccount(account);
        }

        order.setTotalAmount(cartService.getAmount());
        orderService.createOrder(order, cartService.getItems());
        cartService.clear();
        return "redirect:/orders"; // Chuyển sang trang danh sách Order
    }

    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getItems());
        model.addAttribute("totalAmount", cartService.getAmount());
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id") int id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            cartService.add(product, 1);
        }
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable("id") int id) {
        cartService.remove(id);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCart(@RequestParam("id") int id, @RequestParam("qty") int quantity) {
        cartService.update(id, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart() {
        cartService.clear();
        return "redirect:/cart";
    }
}
