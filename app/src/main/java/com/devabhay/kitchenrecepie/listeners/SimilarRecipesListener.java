package com.devabhay.kitchenrecepie.listeners;

import com.devabhay.kitchenrecepie.models.SimilarRecipesResponse;

import java.util.List;

public interface SimilarRecipesListener {
    void didFetch(List<SimilarRecipesResponse> response, String message);
    void didError(String message);
}
