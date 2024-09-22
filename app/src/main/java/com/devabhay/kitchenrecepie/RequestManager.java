package com.devabhay.kitchenrecepie;

import android.content.Context;

import com.devabhay.kitchenrecepie.listeners.InstructionListener;
import com.devabhay.kitchenrecepie.listeners.RandomRecipeResponseListener;
import com.devabhay.kitchenrecepie.listeners.RecipeDetailListener;
import com.devabhay.kitchenrecepie.listeners.SimilarRecipesListener;
import com.devabhay.kitchenrecepie.models.InstructionsResponse;
import com.devabhay.kitchenrecepie.models.RandomRecipeApiResponse;
import com.devabhay.kitchenrecepie.models.RecipeDetailResponse;
import com.devabhay.kitchenrecepie.models.SimilarRecipesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public RequestManager(Context context) {
        this.context = context;
    }

    public void getRandomRecipes(RandomRecipeResponseListener listener, List<String> tags) {
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeApiResponse> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key),"10", tags);
        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
                if (!response.isSuccessful())  {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());

            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable throwable) {
                listener.didError(throwable.getMessage());
            }
        });
    }


    public void getRecipeDetails(RecipeDetailListener listener, int id) {
        CallRecipeDetail callRecipeDetail = retrofit.create(CallRecipeDetail.class);
        Call<RecipeDetailResponse> call = callRecipeDetail.callRecipeDetails(id,context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailResponse> call, Response<RecipeDetailResponse> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailResponse> call, Throwable throwable) {
                listener.didError(throwable.getMessage());
            }
        });
    }

    public void getSimilarRecipes(SimilarRecipesListener listener, int id) {
        CallSimilarRecipes callSimilarRecipes = retrofit.create(CallSimilarRecipes.class);
        Call<List<SimilarRecipesResponse>> call = callSimilarRecipes.callSimilarRecipes(id, context.getString(R.string.api_key),"4");
        call.enqueue(new Callback<List<SimilarRecipesResponse>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipesResponse>> call, Response<List<SimilarRecipesResponse>> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<SimilarRecipesResponse>> call, Throwable throwable) {
                listener.didError(throwable.getMessage());
            }
        });
    }

    public void getInstructions(InstructionListener listener, int id) {
        CallInstruction callInstruction = retrofit.create(CallInstruction.class);
        Call<List<InstructionsResponse>> call = callInstruction.callInstructions(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<List<InstructionsResponse>>() {
            @Override
            public void onResponse(Call<List<InstructionsResponse>> call, Response<List<InstructionsResponse>> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<InstructionsResponse>> call, Throwable throwable) {
                listener.didError(throwable.getMessage());
            }
        });
    }





    private interface CallRandomRecipes {
        @GET("recipes/random")
        Call<RandomRecipeApiResponse> callRandomRecipe (
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags") List<String> tags
        );
    }

    private interface CallRecipeDetail {
        @GET("recipes/{id}/information")
        Call<RecipeDetailResponse> callRecipeDetails (
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }

    private interface CallSimilarRecipes {
        @GET("recipes/{id}/similar")
        Call<List<SimilarRecipesResponse>> callSimilarRecipes (
                @Path("id") int id,
                @Query("apiKey") String apiKey,
                @Query("number") String number
        );

    }

    private interface CallInstruction {
        @GET("recipes/{id}/analyzedInstructions")
        Call<List<InstructionsResponse>> callInstructions (
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }
}
