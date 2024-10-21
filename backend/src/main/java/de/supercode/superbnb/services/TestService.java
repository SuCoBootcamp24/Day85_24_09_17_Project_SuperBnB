package de.supercode.superbnb.services;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TestService {


    private RedisTemplate<String, Object> redisTemplate;
    private static final long TOKEN_EXPIRATION = 30; // 30 Minuten

    public TestService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void storeToken(String email, String token) {
        String key = "passwordReset:" + email;
        redisTemplate.opsForValue().set(key, token, TOKEN_EXPIRATION, TimeUnit.MINUTES);
    }

    public String getToken(String email) {
        String key = "passwordReset:" + email;
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void deleteToken(String email) {
        String key = "passwordReset:" + email;
        redisTemplate.delete(key);
    }
}
