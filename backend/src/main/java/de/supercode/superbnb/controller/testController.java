package de.supercode.superbnb.controller;

import de.supercode.superbnb.services.TestService;
import io.lettuce.core.support.caching.RedisCache;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class testController {

    TestService testService;

    public testController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping
    @CachePut("itemCache")
    public String test(@RequestParam String text) {
        return text;
    }

    @GetMapping
    @Cacheable("itemCache")
    public void test() {

    }


}
