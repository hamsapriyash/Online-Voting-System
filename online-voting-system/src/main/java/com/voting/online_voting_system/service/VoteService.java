package com.voting.online_voting_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.online_voting_system.model.Vote;
import com.voting.online_voting_system.repository.VoteRepository;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepo;

    @Autowired
    private VoterService voterService;

    public void castVote(Long voterId, Long candidateId) {

        if (!voterService.canVote(voterId))
            throw new RuntimeException("Already voted");

        Vote vote = new Vote();
        vote.setVoterId(voterId);
        vote.setCandidateId(candidateId);

        voteRepo.save(vote);
        voterService.markVoted(voterId);
    }
}
