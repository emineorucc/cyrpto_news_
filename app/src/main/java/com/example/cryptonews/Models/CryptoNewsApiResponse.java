package com.example.cryptonews.Models;

import java.io.Serializable;
import  java.util.List;

public class CryptoNewsApiResponse implements Serializable {
    String status;
    int totalResults;
    List<CryptoNewsHeadlines> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<CryptoNewsHeadlines> getArticles() {
        return articles;
    }

    public void setArticles(List<CryptoNewsHeadlines> articles) {
        this.articles = articles;
    }
}
