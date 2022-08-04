package com.example.mvvmtest.model.remote

import com.example.mvvmtest.model.CocktailDetails
import com.example.mvvmtest.model.CocktailItem
import com.example.mvvmtest.model.CocktailSearch
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/*
1. - Retrofit dependencies
2. - create retrofit interface (service)
3. - In the service create the Http verbs
4. - Create the Retrofit object. (singleton)
www.thecocktaildb.com/
api/json/v1/1/search.php
?s=margarita

www.thecocktaildb.com/
api/json/v1/1/lookup.php
?i=11007
 */

interface Service {
    @GET(ENDPOINT_SEARCH)
    fun queryCocktailByName(
        @Query(ARG_SEARCH) searchInput: String
    ) : Call<CocktailSearch>


    @GET(ENDPOINT_DETAIL)
    fun queryCocktailDetails(
        @Query(ARG_DETAIL) cocktailID: String
    ) : Call<CocktailDetails>
}