package com.onlinevotingsystem.VotingSystem.repository;

import com.onlinevotingsystem.VotingSystem.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
    Optional<Voter> findByUsername(String username);

    Optional<Voter> findByEmail(String email);
}
