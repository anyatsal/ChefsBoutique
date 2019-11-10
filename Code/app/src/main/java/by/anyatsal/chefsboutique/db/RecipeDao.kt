package by.anyatsal.chefsboutique.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.anyatsal.chefsboutique.data.Recipe

@Dao
interface RecipeDao {
    @Query("SELECT * FROM Recipe ORDER BY name ASC")
    fun getAll(): LiveData<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(recipe: Recipe)

    @Query("DELETE FROM Recipe")
    suspend fun deleteAll()
}