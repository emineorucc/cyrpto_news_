package com.example.cryptonews;

import com.example.cryptonews.Models.CryptoNewsHeadlines;

import java.util.List;

public interface OnFetchDataListener<CryptoNewsApiResponse>{
    void onFetchData(List<CryptoNewsHeadlines> list, String message);
    void onError(String message);
}
