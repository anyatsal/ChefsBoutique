package by.anyatsal.chefsboutique.api

import by.anyatsal.chefsboutique.data.Recipe
import com.google.gson.annotations.SerializedName

class RecipeResponse {
    @SerializedName("recipes")
    var recipes: List<Recipe>? = null
}