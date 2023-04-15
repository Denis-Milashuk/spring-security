package com.example.milashuk.prePostAuth.controller;

import com.example.milashuk.prePostAuth.domain.Employee;
import com.example.milashuk.prePostAuth.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book/details/{name}")
    public Employee getEmployeeDetails(@PathVariable String name) {
        return bookService.getBookDetails(name);
    }
}
