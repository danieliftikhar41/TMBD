package com.example.fragments.Config;

import com.example.fragments.Model.Film.AccountStates;
import com.example.fragments.Model.Film.searchFilmModel;

import com.example.fragments.Model.List.List;
import com.example.fragments.Model.List.ListModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiCall {
    @GET("search/movie?")
    Call<searchFilmModel> getData(@Query("api_key") String api_key, @Query("query") String query);
    @GET("account/{account_id}/favorite/movies?")
    Call<searchFilmModel> getFev(@Path("account_id") String account_id,@Query(" ") String api_key,@Query("session_id") String session_id );
    @POST("account/{account_id}/favorite?")
    Call<searchFilmModel> setFav(@Path("account_id") String account_id,@Query("api_key") String api_key,@Query("session_id") String session_id,@Query("media_type") String media_type,@Query("media_id") Integer media_id,@Query("favorite") boolean favorite);
    @POST("list?")
    Call<searchFilmModel> setList(@Query("api_key") String api_key,@Query("session_id") String session_id ,@Body List list);
    @GET("movie/{movie_id}/account_states?")
    Call<AccountStates> account_state(@Path("movie_id") Integer movie_id, @Query("api_key") String api_key, @Query("session_id") String session_id);
    @GET("account/{account_id}/lists?")
    Call<ListModel> getList(@Path("account_id") String account_id, @Query("api_key") String api_key, @Query("session_id") String session_id );
}
