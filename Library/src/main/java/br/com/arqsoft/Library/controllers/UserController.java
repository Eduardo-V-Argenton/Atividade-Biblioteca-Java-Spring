package br.com.arqsoft.Library.controllers;

import br.com.arqsoft.Library.dto.UserModelDto;
import br.com.arqsoft.Library.enums.RoleName;
import br.com.arqsoft.Library.models.RoleModel;
import br.com.arqsoft.Library.models.UserModel;
import br.com.arqsoft.Library.repositories.RentRepository;
import br.com.arqsoft.Library.repositories.RoleRepository;
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
import java.util.Optional;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    final UserRepository userRepository;
    final RoleRepository roleRepository;

    public UserController(UserRepository userRepository, RentRepository rentRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
        ModelAndView mv = new ModelAndView("/users/new");
        mv.addObject("roles", RoleName.values());
        return mv;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("")
    public ModelAndView create(@Valid UserModelDto udto, BindingResult bdr) {
        if (bdr.hasErrors()) {
            ModelAndView mv = new ModelAndView("/users/new");
            mv.addObject("roles", RoleName.values());
            return mv;
        } else {
            UserModel user = new UserModel();
            Optional<RoleModel> optional = this.roleRepository.findByRoleName(udto.getRoles());
            if (optional.isPresent()) {
                RoleModel role = optional.get();
                user = udto.toUser(user, role);
                this.userRepository.save(user);
                return new ModelAndView("redirect:/users");
            } else {
                return new ModelAndView("error");
            }
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
