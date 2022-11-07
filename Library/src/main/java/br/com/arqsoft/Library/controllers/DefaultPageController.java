package br.com.arqsoft.Library.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultPageController {

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("")
    public ModelAndView default_page() {
        return new ModelAndView("redirect:/books");
    }

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("users/login");
    }
}


