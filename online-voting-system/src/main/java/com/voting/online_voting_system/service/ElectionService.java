package com.voting.online_voting_system.service;

import com.voting.online_voting_system.model.Election;
import com.voting.online_voting_system.repository.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ElectionService {

    @Autowired
    private ElectionRepository electionRepository;

    // ✅ Create new election
    public Election createElection(Election election) {
        return electionRepository.save(election);
    }

    // ✅ Get all elections
    public List<Election> getAllElections() {
        return electionRepository.findAll();
    }

    // ✅ Get election by ID
    public Optional<Election> getElectionById(Long id) {
        return electionRepository.findById(id);
    }

    // ✅ Check if election is active based on time
    public boolean isElectionActive(Election election) {
        LocalDateTime now = LocalDateTime.now();

        return now.isAfter(election.getStartTime())
                && now.isBefore(election.getEndTime());
    }

    // ✅ Get currently active election (if any)
    public Optional<Election> getActiveElection() {
        LocalDateTime now = LocalDateTime.now();

        return electionRepository.findAll()
                .stream()
                .filter(e -> now.isAfter(e.getStartTime())
                        && now.isBefore(e.getEndTime()))
                .findFirst();
    }

    // ✅ Delete election
    public void deleteElection(Long id) {
        electionRepository.deleteById(id);
    }
}
