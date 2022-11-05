/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.arqsoft.Library.controllers;


import javax.validation.Valid;

import br.com.arqsoft.Library.dto.BookModelDto;
import br.com.arqsoft.Library.models.BookModel;
import br.com.arqsoft.Library.repositories.BookRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

/**
 * @author eduardo
 */
@Controller
@RequestMapping(value = "/books")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("")
    public ModelAndView index() {
        List<BookModel> books = this.bookRepository.findAll();
        ModelAndView mv = new ModelAndView("books/index");
        mv.addObject("books", books);
        return mv;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/new")
    public ModelAndView nnew(BookModelDto bdto) {
        return new ModelAndView("/books/new");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("")
    public ModelAndView create(@Valid BookModelDto bdto, BindingResult bdr) {
        if (bdr.hasErrors()) {
            return new ModelAndView("/books/new");
        } else {
            BookModel book = new BookModel();
            book = bdto.toBook(book);
            this.bookRepository.save(book);
            return new ModelAndView("redirect:/books");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Integer id, BookModelDto bdto) {
        Optional<BookModel> optional = this.bookRepository.findById(id);

        if (optional.isPresent()) {
            BookModel book = optional.get();
            ModelAndView mv = new ModelAndView("/books/edit");
            mv.addObject("bookId", book.getId());
            return mv;
        } else {
            return new ModelAndView("redirect:/errors");

        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Integer id, @Valid BookModelDto bdto, BindingResult bdr) {
        if (bdr.hasErrors()) {
            return new ModelAndView("/books/" + id + "/edit");
        } else {
            Optional<BookModel> optional = this.bookRepository.findById(id);

            if (optional.isPresent()) {
                BookModel book = optional.get();
                book = bdto.toBook(book);
                this.bookRepository.save(book);
                return new ModelAndView("redirect:/books");
            } else {
                return new ModelAndView("redirect:/errors");
            }
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Integer id) {
        try {
            this.bookRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException(e);
        }
        return new ModelAndView("redirect:/books");
    }
}  

