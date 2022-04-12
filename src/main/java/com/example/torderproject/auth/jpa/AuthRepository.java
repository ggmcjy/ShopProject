package com.example.torderproject.auth.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<AuthToken, Long> {
    AuthToken findByAccountUsername(String subject);

    AuthToken findByAccountId(Long id);
}
