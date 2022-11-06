package br.com.arqsoft.Library.controllers;

import br.com.arqsoft.Library.models.BookModel;
import br.com.arqsoft.Library.models.RentModel;
import br.com.arqsoft.Library.models.UserModel;
import br.com.arqsoft.Library.repositories.BookRepository;
import br.com.arqsoft.Library.repositories.RentRepository;
import br.com.arqsoft.Library.repositories.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/rents")
public class rentController {
    final UserRepository userRepository;
    final BookRepository bookRepository;
    final RentRepository rentRepository;

    public rentController(UserRepository userRepository, BookRepository bookRepository, RentRepository rentRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.rentRepository = rentRepository;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("")
    public ModelAndView rents() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserModel> optional_user = this.userRepository.findByUsername(auth.getName());
        if (optional_user.isPresent()) {
            UserModel user = optional_user.get();
            Optional<List<RentModel>> optional_rent = this.rentRepository.findAllByUser(user);
            if (optional_rent.isPresent()) {
                List<RentModel> rent = optional_rent.get();
                ModelAndView mv = new ModelAndView("rents/index");
                mv.addObject("rents", rent);
                return mv;
            }
        }
        return new ModelAndView("redirect:/errors");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public ModelAndView rent_admin() {
        List<RentModel> rent = this.rentRepository.findAll();
        ModelAndView mv = new ModelAndView("rents/index");
        mv.addObject("rents", rent);
        return mv;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/rent/{id}")
    public ModelAndView rent(@PathVariable Integer id) {
        Optional<BookModel> optional = this.bookRepository.findById(id);

        if (optional.isPresent()) {
            BookModel book = optional.get();
            if (book.getAvailable()) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                Optional<UserModel> optional_user = this.userRepository.findByUsername(auth.getName());
                if (optional_user.isPresent()) {
                    UserModel user = optional_user.get();
                    RentModel rent = new RentModel(user, book);
                    this.rentRepository.save(rent);
                    book.setAvailable(false);
                    this.bookRepository.save(book);
                    return new ModelAndView("redirect:/rents");
                }
            }
        }
        return new ModelAndView("redirect:/errors");
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}/return")
    public ModelAndView return_book(@PathVariable Integer id) {
        Optional<RentModel> optional = this.rentRepository.findById(id);
        if (optional.isPresent()) {
            System.out.println("1");
            RentModel rent = optional.get();
            if (rent.getReturnDate() == null) {
                System.out.println("2");
                Optional<BookModel> optional_book = this.bookRepository.findById(rent.getBook().getId());
                if (optional_book.isPresent()) {
                    BookModel book = optional_book.get();
                    if (!book.getAvailable()) {
                        rent.setReturnDate(LocalDate.now());
                        this.rentRepository.save(rent);
                        book.setAvailable(true);
                        this.bookRepository.save(book);
                        return new ModelAndView("redirect:/rents");
                    }
                }
            }
        }
        return new ModelAndView("redirect:/errors");
    }
}
