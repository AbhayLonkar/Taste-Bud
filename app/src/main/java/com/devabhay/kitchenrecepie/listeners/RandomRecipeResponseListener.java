package com.devabhay.kitchenrecepie.listeners;

import com.devabhay.kitchenrecepie.models.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {
    void didFetch(RandomRecipeApiResponse response, String message);
    void didError(String message);
}
