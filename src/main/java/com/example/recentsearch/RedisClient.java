package com.example.recentsearch;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RedisClient {

    private final RedisTemplate<String, String> redisTemplate;

    public List<SearchRecord> getSearchHistory() {
        Set<ZSetOperations.TypedTuple<String>> tenMostRecentRecords
                = redisTemplate.opsForZSet().reverseRangeWithScores("searchHistory", 0, 10);
        return tenMostRecentRecords.stream()
                .map(record -> new SearchRecord(
                        record.getValue(),
                        record.getScore()
                )).collect(Collectors.toList());
    }

    public void addSearchHistory(String searchTerm) {
        redisTemplate.opsForZSet().add("searchHistory", searchTerm, System.currentTimeMillis());
    }
}
