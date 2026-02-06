package com.voting.online_voting_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.voting.online_voting_system.repository.CandidateRepository;
import com.voting.online_voting_system.service.VoteService;

@Controller
public class VotingController {

    @Autowired
    private CandidateRepository candidateRepo;

    @Autowired
    private VoteService voteService;

    @GetMapping("/vote")
    public String showVotePage(Model model) {
        model.addAttribute("candidates", candidateRepo.findAll());
        return "voting";
    }

    @PostMapping("/vote")
    public String vote(@RequestParam Long voterId,
            @RequestParam Long candidateId) {

        voteService.castVote(voterId, candidateId);
        return "redirect:/results";
    }
}
