package ru.skypro.homework.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CommentListResponse {
    private Integer count;
    private List<CommentResponse> results;

    public CommentListResponse(Integer count, List<CommentResponse> results) {
        this.count = count;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<CommentResponse> getResults() {
        return results;
    }

    public void setResults(List<CommentResponse> results) {
        this.results = results;
    }
}
