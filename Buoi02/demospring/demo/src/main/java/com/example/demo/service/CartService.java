package com.example.demo.service;

import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class CartService {
    private List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        Optional<CartItem> existingItem = items.stream()
                .filter(item -> item.getProductId() == product.getId())
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        } else {
            items.add(new CartItem(product.getId(), product.getName(), product.getPrice(), quantity));
        }
    }

    public void remove(int productId) {
        items.removeIf(item -> item.getProductId() == productId);
    }

    public void update(int productId, int quantity) {
        items.stream()
                .filter(item -> item.getProductId() == productId)
                .findFirst()
                .ifPresent(item -> item.setQuantity(quantity));
    }

    public void clear() {
        items.clear();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public int getCount() {
        return items.stream().mapToInt(CartItem::getQuantity).sum();
    }

    public long getAmount() {
        return items.stream().mapToLong(item -> item.getPrice() * item.getQuantity()).sum();
    }
}
