package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private List<Product> listProduct = new ArrayList<>();

    public ProductService() {
    }

    public List<Product> getAllProducts() {
        return listProduct;
    }

    public Product getProductById(int id) {
        Optional<Product> optional = listProduct.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
        return optional.orElse(null);
    }

    public void addProduct(Product newProduct) {
        if (listProduct.isEmpty()) {
            newProduct.setId(1);
        } else {
            int maxId = listProduct.stream()
                    .mapToInt(Product::getId)
                    .max()
                    .orElse(0);
            newProduct.setId(maxId + 1);
        }
        listProduct.add(newProduct);
    }

    public void updateProduct(Product product) {
        for (int i = 0; i < listProduct.size(); i++) {
            if (listProduct.get(i).getId() == product.getId()) {
                listProduct.set(i, product);
                return;
            }
        }
    }

    public void deleteProduct(int id) {
        listProduct.removeIf(p -> p.getId() == id);
    }
}
