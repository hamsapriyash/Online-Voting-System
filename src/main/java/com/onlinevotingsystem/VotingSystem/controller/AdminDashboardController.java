package com.onlinevotingsystem.VotingSystem.controller;

import com.onlinevotingsystem.VotingSystem.model.Party;
import com.onlinevotingsystem.VotingSystem.model.Voter;
import com.onlinevotingsystem.VotingSystem.repository.PartyRepository;
import com.onlinevotingsystem.VotingSystem.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String index() {
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("voterCount", voterRepository.count());
        model.addAttribute("partyCount", partyRepository.count());
        return "admin-dashboard";
    }

    @GetMapping("/add-voter")
    public String showAddVoterForm() {
        return "admin-add-voter";
    }

    @PostMapping("/add-voter")
    public String addVoter(@RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String name,
            Model model) {
        if (voterRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error", "Username already exists");
            return "admin-add-voter";
        }

        Voter voter = new Voter();
        voter.setUsername(username);
        voter.setPassword(passwordEncoder.encode(password));
        voter.setEmail(email);
        voter.setName(name);
        voterRepository.save(voter);

        return "redirect:/admin/dashboard?success=voter";
    }

    @GetMapping("/add-party")
    public String showAddPartyForm() {
        return "admin-add-party";
    }

    @PostMapping("/add-party")
    public String addParty(@RequestParam String name,
            @RequestParam String symbolUrl,
            @RequestParam String description,
            Model model) {
        if (partyRepository.findByName(name).isPresent()) {
            model.addAttribute("error", "Party name already exists");
            return "admin-add-party";
        }

        Party party = new Party();
        party.setName(name);
        party.setSymbolUrl(symbolUrl);
        party.setDescription(description);
        partyRepository.save(party);

        return "redirect:/admin/dashboard?success=party";
    }
}
