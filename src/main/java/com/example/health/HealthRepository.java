package com.example.health;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthRepository extends JpaRepository<Health,Long> {
}
