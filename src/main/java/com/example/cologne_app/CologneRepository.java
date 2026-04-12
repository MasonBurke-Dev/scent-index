package com.example.cologne_app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CologneRepository extends JpaRepository<Cologne, Long> {
}