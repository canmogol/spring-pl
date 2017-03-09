package com.fererlab.controller;

import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class AuthenticationController {

    private Log log;

    @RequestMapping(value = "/account/login")
    public String login(Model model) {
        model.addAttribute("authenticationUser", new AuthenticationUser());
        return "authenticate/login";
    }

    @RequestMapping(value = "/account/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("registrationUser", new RegistrationUser());
        return "authenticate/register";
    }

    @RequestMapping(value = "/account/register", method = RequestMethod.POST)
    public String register(@Valid RegistrationUser registrationUser,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("registrationUser", registrationUser);
            return "authenticate/register";
        }
        return "redirect:/";
    }
}
