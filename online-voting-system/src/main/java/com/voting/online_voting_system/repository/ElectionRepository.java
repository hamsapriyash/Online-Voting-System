package com.voting.online_voting_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voting.online_voting_system.model.Election;

@Repository

public interface ElectionRepository extends JpaRepository<Election, Long> {
}
