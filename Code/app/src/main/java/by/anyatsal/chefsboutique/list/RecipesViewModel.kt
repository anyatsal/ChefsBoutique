package by.anyatsal.chefsboutique.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import by.anyatsal.chefsboutique.data.Recipe
import by.anyatsal.chefsboutique.db.AppDatabase
import by.anyatsal.chefsboutique.repository.RecipesRepository
import kotlinx.coroutines.launch

class RecipesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecipesRepository
    val recipes: LiveData<List<Recipe>>

    init {
        val recipesDao = AppDatabase.getDatabase(application).recipeDao()
        repository = RecipesRepository(recipesDao)
        recipes = repository.recipes
    }

    fun insert(recipe: Recipe) = viewModelScope.launch {
        repository.insert(recipe)
    }
}