package net.javaguides.springboot.web;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.javaguides.springboot.service.BaseMoneyService;
import net.javaguides.springboot.service.UserService;
import net.javaguides.springboot.web.dto.UserRegistrationDto;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    private ArrayList<UserRegistrationDto> users = new ArrayList<>();
    @Autowired
    private UserService userService;

    @Autowired
    private BaseMoneyService baseMoneyService;

    @ModelAttribute("currentUser")
    public UserRegistrationDto currentUserRegistrationDto() {
        return new UserRegistrationDto();
    }

    @ModelAttribute("addUser")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public ModelAndView showPaymentView() {
        if (users.size() == 0) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String userName;

            if (principal instanceof UserDetails) {
                userName = ((UserDetails) principal).getUsername();
            } else {
                userName = principal.toString();
            }

            UserRegistrationDto user = userService.getUser(userName);
            users.add(user);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("payment");
        modelAndView.addObject("currentUsers", users);

        return modelAndView;
    }

    @GetMapping("/addPayment")
    public String showAddPaymentView() {
        return "addPayment";
    }

    @GetMapping(value = "/tinhtien")
    public ModelAndView showTinhTienView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tinhtien");
        modelAndView.addObject("currentUsers", users);
        modelAndView.addObject("tongtien",
                calculateMoney(baseMoneyService.getBaseMoney().getBaseMoney(), users.size()));
        return modelAndView;
    }

    @GetMapping(value = "/trangchu")
    public String showTrangChu() {
        users = new ArrayList<>();
        return "index";
    }

    @GetMapping(value = "/hoadon")
    public ModelAndView showHoaDon() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hoadon");
        modelAndView.addObject("tongtien",
                calculateMoney(baseMoneyService.getBaseMoney().getBaseMoney(), users.size()));
        return modelAndView;
    }

    @PostMapping(value = "/addUser")
    public String addUser(@ModelAttribute("addUser") UserRegistrationDto addUser) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName;

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }

        if (!userService.checkHoKhau(addUser.getCmnd(), userName)) {
            return "redirect:/payment/addPayment?error";
        }
        users.add(addUser);
        return "redirect:/payment/addPayment?success";
    }

    @PostMapping(value = "/removeUser")
    public String removeUser(@ModelAttribute("currentUser") UserRegistrationDto removeUser) {
        if (users.contains(removeUser)) {
            users.remove(removeUser);
        }
        return "payment";
    }

    private Float calculateMoney(Float money, Integer ammountOfPeople) {
        Float firstPerson = money * 0.045f;
        Float sumOfMoney = firstPerson;

        if (ammountOfPeople >= 2) {
            sumOfMoney += firstPerson * 0.7f;
        }

        if (ammountOfPeople >= 3) {
            sumOfMoney += firstPerson * 0.6f;
        }

        if (ammountOfPeople >= 4) {
            sumOfMoney += firstPerson * 0.5f;
        }

        for (int i = 5; i <= ammountOfPeople; i++) {
            sumOfMoney += firstPerson * 0.4f;
        }

        return sumOfMoney;
    }

}
