package br.com.arqsoft.Library.controllers;

import br.com.arqsoft.Library.dto.UserModelDto;
import br.com.arqsoft.Library.models.UserModel;
import br.com.arqsoft.Library.repositories.RentRepository;
import br.com.arqsoft.Library.repositories.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    final UserRepository userRepository;

    public UserController(UserRepository userRepository, RentRepository rentRepository) {
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("")
    public ModelAndView index() {
        List<UserModel> users = this.userRepository.findAll();
        ModelAndView mv = new ModelAndView("users/index");
        mv.addObject("users", users);
        return mv;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/new")
    public ModelAndView nnew(UserModelDto udto) {
        return new ModelAndView("/users/new");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("")
    public ModelAndView create(@Valid UserModelDto udto, BindingResult bdr) {
        if (bdr.hasErrors()) {
            return new ModelAndView("/users/new");
        } else {
            UserModel user = new UserModel();
            user = udto.toUser(user);
            this.userRepository.save(user);
            return new ModelAndView("redirect:/users");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Integer id) {
        try {
            this.userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException(e);
        }
        return new ModelAndView("redirect:/users");
    }
}
