package by.anyatsal.chefsboutique.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import by.anyatsal.chefsboutique.R
import by.anyatsal.chefsboutique.activity.BaseActivity.Companion.changeFragment
import by.anyatsal.chefsboutique.data.Recipe
import by.anyatsal.chefsboutique.list.RecipesAdapter
import by.anyatsal.chefsboutique.list.RecipesViewModel

class RecipeListFragment : Fragment() {

    private lateinit var recipesViewModel: RecipesViewModel

    companion object {
        fun newInstance() = RecipeListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipes, container, false)
        val rv = view.findViewById<RecyclerView>(R.id.recipes_list)
        val adapter = RecipesAdapter(context!!)
        rv.adapter = adapter

        recipesViewModel = ViewModelProvider(this).get(RecipesViewModel::class.java)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        recipesViewModel.recipes.observe(this, Observer { recipes ->
            // Update the cached copy of the words in the adapter.
            recipes?.let { adapter.setRecipes(recipes) }
        })

        return view
    }

    private fun showDetailsFragment(recipes: List<Recipe>, position: Int) {
        changeFragment(context, RecipeFragment.newInstance(recipes[position]))
    }
}