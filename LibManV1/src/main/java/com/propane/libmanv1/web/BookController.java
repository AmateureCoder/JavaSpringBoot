package com.propane.libmanv1.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.propane.libmanv1.identity.model.Book;
import com.propane.libmanv1.identity.service.imp.BookServiceImpl;

@Controller
@RequestMapping("/admin/books")
@RequiredArgsConstructor
public class BookController {
    private final BookServiceImpl bookServiceImpl;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookServiceImpl.getAllBooks());
        return "admin/books/list";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "admin/books/add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        bookServiceImpl.saveBook(book);
        return "redirect:/admin/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookServiceImpl.getBookById(id));
        return "admin/books/edit";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute Book book) {
        book.setId(id);
        bookServiceImpl.saveBook(book);
        return "redirect:/admin/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookServiceImpl.deleteBook(id);
        return "redirect:/admin/books";
    }
}