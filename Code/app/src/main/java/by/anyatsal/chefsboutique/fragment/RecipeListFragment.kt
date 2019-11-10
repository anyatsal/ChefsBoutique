package by.anyatsal.chefsboutique.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import by.anyatsal.chefsboutique.R
import by.anyatsal.chefsboutique.activity.BaseActivity.Companion.changeFragment
import by.anyatsal.chefsboutique.adapters.RecipeDBAdapter
import by.anyatsal.chefsboutique.data.RecipeDBItem

class RecipeListFragment : Fragment() {

    companion object {
        private const val ARGS_RECIPES = "ARGS_RECIPES"

        fun newInstance(
            recipeDBItems: List<RecipeDBItem>
        ): RecipeListFragment {
            val fragment = RecipeListFragment()
            val bundle = Bundle()
            bundle.run {
                putParcelableArrayList(ARGS_RECIPES, ArrayList(recipeDBItems))
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipes, container, false)
        val rv = view.findViewById<RecyclerView>(R.id.recipes_list)
        val recipes = arguments?.getParcelableArrayList<RecipeDBItem>(ARGS_RECIPES)
            ?: throw IllegalArgumentException("Missing recipes argument")
        val adapter = RecipeDBAdapter(context, recipes) { position ->
            showDetailsFragment(recipes, position)
        }
        rv.adapter = adapter

        return view
    }

    private fun showDetailsFragment(recipeDBItems: List<RecipeDBItem>, position: Int) {
        changeFragment(context, RecipeFragment.newInstance(recipeDBItems[position]))
    }
}