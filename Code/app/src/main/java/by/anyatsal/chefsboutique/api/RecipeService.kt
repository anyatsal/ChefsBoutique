package by.anyatsal.chefsboutique.api

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {
    @GET("api/search?")
    fun getRecipeList(@Query("key") key: String, @Query("q") q: String): Observable<RecipeResponse>

    companion object {
        fun create(): RecipeService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.food2fork.com/")
                .build()

            return retrofit.create(RecipeService::class.java)
        }
    }
}