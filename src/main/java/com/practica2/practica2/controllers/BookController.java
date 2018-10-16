package com.practica2.practica2.controllers;

import com.practica2.practica2.entities.Book;
import com.practica2.practica2.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {
    BookService bookService;

    @Autowired
    public void setBookService(BookService bookservice) {this.bookService=bookservice;}

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String index( Model model) {
        List<Book> books  = (List) bookService.listAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @RequestMapping(value = "/book/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model) {
        Book book = bookService.findBook(id);
        model.addAttribute("book", book);
       // model.addAttribute("errorLikes", "");
        return "editBook";
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Integer id, Model model) {
        Book book = bookService.findBook(id);
        model.addAttribute("book", book);
        return "show";
    }
    @RequestMapping(value = "/book/new", method = RequestMethod.GET)
    public String newBook( Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("errorLikes", "");
        return "newBook";
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, Model model) {
       /* if(bindingResult.hasErrors()){
            String errorLikes =bindingResult.getFieldError("likes").getDefaultMessage();
            model.addAttribute("errorLikes", errorLikes);
            return "newBook";
        }*/
        if(book.getLikes() < 0)
            book.setLikes(0);
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @RequestMapping(value= "/book/delete/{id}")
    public String delete(@PathVariable Integer id)
    {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @RequestMapping(value = "/book/like/{id}", method = RequestMethod.GET)
    public String like(@PathVariable Integer id){
        Book book = bookService.findBook(id);
        book.setLikes(book.getLikes()+1);
        bookService.saveBook(book);
        return "redirect:/books";
    }
}
