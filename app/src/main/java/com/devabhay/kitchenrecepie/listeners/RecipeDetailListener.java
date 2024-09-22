package com.devabhay.kitchenrecepie.listeners;

import com.devabhay.kitchenrecepie.models.RecipeDetailResponse;

public interface RecipeDetailListener {
    void didFetch(RecipeDetailResponse response, String message);
    void didError(String message);
}
