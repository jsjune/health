package com.example.health;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{dynamicPath}")
public class HealthController {
    private final HealthRepository healthRepository;
    private final StringRedisTemplate stringRedisTemplate;

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

    @GetMapping("/redis/set")
    public String setRedisValue(@RequestParam String key, @RequestParam String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        return "key : " + key + " value : " + value;
    }

    @GetMapping("/redis/get/{key}")
    public String getRedisValue(@PathVariable String key) {
        return "value: " + stringRedisTemplate.opsForValue().get(key);
    }
}
