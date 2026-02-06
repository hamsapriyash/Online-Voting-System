package com.voting.online_voting_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.online_voting_system.model.Voter;
import com.voting.online_voting_system.repository.VoterRepository;

@Service
public class VoterService {
    @Autowired
    private VoterRepository voterRepo;

    public Voter register(Voter voter) {
        voter.setHasVoted(false);
        return voterRepo.save(voter);
    }

    public boolean canVote(Long voterId) {
        return !voterRepo.findById(voterId).get().isHasVoted();
    }

    public void markVoted(Long voterId) {
        Voter v = voterRepo.findById(voterId).get();
        v.setHasVoted(true);
        voterRepo.save(v);
    }

}
