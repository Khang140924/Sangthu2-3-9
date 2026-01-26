package com.example.demo.service;

import com.example.demo.model.Book;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();

    // Tạo sẵn một vài cuốn sách khi chạy chương trình
    public BookService() {
        books.add(new Book(1, "Lap trinh Java", "Nguyen Van A"));
        books.add(new Book(2, "Spring Boot Co ban", "Tran Van B"));
    }

    // Lấy tất cả sách
    public List<Book> getAllBooks() {
        return books;
    }

    // Lấy sách theo ID
    public Book getBookById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    // Thêm sách mới
    public void addBook(Book book) {
        books.add(book);
    }

    // Cập nhật sách
    public void updateBook(int id, Book bookMoi) {
        for (Book b : books) {
            if (b.getId() == id) {
                b.setTitle(bookMoi.getTitle());
                b.setAuthor(bookMoi.getAuthor());
                break;
            }
        }
    }

    // Xóa sách
    public void deleteBook(int id) {
        books.removeIf(b -> b.getId() == id);
    }
}