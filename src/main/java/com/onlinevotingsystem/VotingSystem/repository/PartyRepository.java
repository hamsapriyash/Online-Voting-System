package com.onlinevotingsystem.VotingSystem.repository;

import com.onlinevotingsystem.VotingSystem.model.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
    Optional<Party> findByName(String name);
}
