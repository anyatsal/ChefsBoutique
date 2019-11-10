package by.anyatsal.chefsboutique.api

import by.anyatsal.chefsboutique.data.RecipeSearchItem
import com.google.gson.annotations.SerializedName

class RecipeResponse {
    @SerializedName("recipes")
    var recipes: List<RecipeSearchItem>? = null
}