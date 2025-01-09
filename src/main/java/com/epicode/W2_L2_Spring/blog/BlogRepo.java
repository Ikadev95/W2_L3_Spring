package com.epicode.W2_L2_Spring.blog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepo extends JpaRepository<Blog,Long> {
    boolean existsByTitolo(String titolo);
}
