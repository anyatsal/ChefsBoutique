package by.anyatsal.chefsboutique.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private const val DATABASE_NAME = "recipes.db"
        private val DATABASE_VERSION = 1
    }

    fun insertRecipe(recipeDBItem: RecipeDBItem): Boolean {
        return true
    }

    fun readRecipe(recipeId: String): Boolean {
        return true
    }

    fun readAllRecipes(): List<RecipeDBItem> {
        return ArrayList()
    }
}