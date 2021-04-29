package com.example.restaurantapp.data;

import java.util.List;

public class PlaceResult{
    public List<Object> html_attributions;
    public List<Result> results;

    public List<Object> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(List<Object> html_attributions) {
        this.html_attributions = html_attributions;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String status;
}
