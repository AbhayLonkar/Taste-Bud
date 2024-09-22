package com.devabhay.kitchenrecepie.listeners;

import com.devabhay.kitchenrecepie.models.InstructionsResponse;

import java.util.List;

public interface InstructionListener {
    void didFetch(List<InstructionsResponse> response, String message);
    void didError(String message);
}
