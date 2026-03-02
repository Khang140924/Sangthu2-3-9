package com.example.demo.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class Product {
    private int id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    @NotNull(message = "Giá sản phẩm không được để trống")
    @Min(value = 1, message = "Giá sản phẩm phải từ 1 đến 9999999")
    @Max(value = 9999999, message = "Giá sản phẩm phải từ 1 đến 9999999")
    private Integer price;

    @Length(max = 200, message = "Tên hình ảnh không quá 200 kí tự")
    private String imageName;

    private String category;

    public Product() {
    }

    public Product(int id, String name, Integer price, String imageName, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageName = imageName;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
