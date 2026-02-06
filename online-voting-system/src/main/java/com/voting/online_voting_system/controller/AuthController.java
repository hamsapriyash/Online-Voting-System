package com.voting.online_voting_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.voting.online_voting_system.model.Voter;
import com.voting.online_voting_system.service.VoterService;

@Controller
public class AuthController {

    @Autowired
    private VoterService voterService;

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("voter", new Voter());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Voter voter) {
        voterService.register(voter);
        return "login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
