package com.voting.online_voting_system.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.voting.online_voting_system.model.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
}
