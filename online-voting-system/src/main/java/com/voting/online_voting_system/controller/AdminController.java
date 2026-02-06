package com.voting.online_voting_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voting.online_voting_system.model.Candidate;
import com.voting.online_voting_system.repository.CandidateRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CandidateRepository candidateRepo;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("candidates", candidateRepo.findAll());
        return "admin-dashboard";
    }

    @PostMapping("/add-candidate")
    public String addCandidate(Candidate c) {
        candidateRepo.save(c);
        return "redirect:/admin/dashboard";
    }
}
