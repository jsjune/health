package com.example.health;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/k654bb98e3b9ba")
public class HealthController {
    private final HealthRepository healthRepository;

    @GetMapping("/health")
    public String health() {
        return "ok-v3\n";
    }

    @GetMapping("/health/save")
    public String save() {
        healthRepository.save(new Health(UUID.randomUUID().toString()));
        return "save\n";
    }

    @GetMapping("/health/{id}")
    public Health getHealth(@PathVariable Long id) {
        return healthRepository.findById(id).orElse(null);
    }
}
