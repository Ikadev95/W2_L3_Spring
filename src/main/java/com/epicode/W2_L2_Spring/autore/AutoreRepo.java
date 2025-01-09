package com.epicode.W2_L2_Spring.autore;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoreRepo extends JpaRepository<Autore, Long> {
    boolean existsByEmail(String email);
    Autore findByEmail(String email);
}
