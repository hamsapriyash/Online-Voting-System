package com.onlinevotingsystem.VotingSystem.controller;

import com.onlinevotingsystem.VotingSystem.model.Party;
import com.onlinevotingsystem.VotingSystem.model.Voter;
import com.onlinevotingsystem.VotingSystem.repository.PartyRepository;
import com.onlinevotingsystem.VotingSystem.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/voter")
public class VoterController {

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private PartyRepository partyRepository;

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Voter voter = voterRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Voter not found"));

        List<Party> parties = partyRepository.findAll();

        model.addAttribute("voter", voter);
        model.addAttribute("parties", parties);

        return "voter-dashboard";
    }

    @PostMapping("/vote")
    public String vote(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Long partyId) {
        Voter voter = voterRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Voter not found"));

        if (!voter.isHasVoted()) {
            voter.setHasVoted(true);
            voterRepository.save(voter);
            // In a real app, you'd also increment the party's vote count here
            return "redirect:/voter/dashboard?success=voted";
        }

        return "redirect:/voter/dashboard?error=already_voted";
    }
}
