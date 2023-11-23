package com.example.bookingsystembackend.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@ComponentScan(basePackages = "com.example.kinoxp.Backend.controller")

@Controller
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {


    @GetMapping("/login")
    public String loginDisplay() {
        return "login";
    }

    @GetMapping("/homepage")
    public String homepageDisplay(){
        return "homepage";
    }


    @GetMapping("/customerInfo/{customerId}")
    public String customerinfoDisplay(){
        return "customerInfo";
    }

    @GetMapping("/customerList")
    public String customerListDisplay(){
        return "customerList";
    }


    @GetMapping("/deleteCustomer")
    public String deleteCustomer(){
        return "deleteCustomer";
    }

    @GetMapping("/signupCustomer")
    public String signupCustomer(){
        return "signupCustomer";
    }

    @GetMapping("/editCustomer")
    public String editCustomer() {
        return "editCustomer";
    }

    @GetMapping("/navbar")
    public String navbar(){
        return "navbar";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalidate the session
        session.invalidate();
        // Redirect to the login page or another appropriate page
        return "redirect:/login";
    }












}
