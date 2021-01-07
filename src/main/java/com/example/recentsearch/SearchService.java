package com.example.recentsearch;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchService {

    private final RedisClient redisClient;

    public List<SearchRecord> getSearchHistory() {
        return redisClient.getSearchHistory();
    }

    public void search(String searchTerm) {
        // Do search
        redisClient.addSearchHistory(searchTerm);
    }
}
