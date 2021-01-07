package com.example.recentsearch;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/searchHistory")
    public List<SearchRecord> getSearchHistory() {
        return searchService.getSearchHistory();
    }

    @GetMapping("/search")
    public void search(
            @RequestParam String searchTerm
    ) {
        searchService.search(searchTerm);
    }

}
