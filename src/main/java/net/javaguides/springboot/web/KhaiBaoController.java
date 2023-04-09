package net.javaguides.springboot.web;

import net.javaguides.springboot.service.UserService;
import net.javaguides.springboot.web.dto.UserRegistrationDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/khaibao")
public class KhaiBaoController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView showKhaiBao() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName;

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("khaibao");
        UserRegistrationDto userRegistrationDto = userService.getUser(userName);
        modelAndView.addObject("currentUser", userRegistrationDto);
        return modelAndView;
    }

    @PostMapping
    public String updateDataBase(@ModelAttribute("currentUser") UserRegistrationDto registrationDto) {
        userService.update(registrationDto);
        return "redirect:khaibao?success";
    }
}
