package by.anyatsal.chefsboutique.repository

import androidx.lifecycle.LiveData
import by.anyatsal.chefsboutique.data.Recipe
import by.anyatsal.chefsboutique.db.RecipeDao

class RecipesRepository(private val recipeDao: RecipeDao) {

    val recipes: LiveData<List<Recipe>> = recipeDao.getAll()

    suspend fun insert(recipe: Recipe) {
        recipeDao.insert(recipe)
    }
}