package me.ronygomes.reference.springboot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ErrorDetails {
    
    private LocalDateTime timestamp;
    private String summary;
    private String path;
    private List<String> errors;

    public ErrorDetails() {
        this.errors = new ArrayList<>();
    }

    public ErrorDetails(LocalDateTime timestamp, String summary,
                        String path, List<String> errors) {

        this.timestamp = timestamp;
        this.summary = summary;
        this.path = path;
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getSummary() {
        return summary;
    }

    public String getPath() {
        return path;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}


