package by.anyatsal.chefsboutique.api

class MovieRepository(private val api : RecipeApi) : BaseRepository() {

    fun getPopularMovies() : MutableList<TmdbMovie>?{

        //safeApiCall is defined in BaseRepository.kt (https://gist.github.com/navi25/67176730f5595b3f1fb5095062a92f15)
        val movieResponse = safeApiCall(
            call = {api.getPopularMovie().await()},
            errorMessage = "Error Fetching Popular Movies"
        )

        return movieResponse?.results.toMutableList();

    }

}