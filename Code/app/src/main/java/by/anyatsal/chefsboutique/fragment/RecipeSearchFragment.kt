package by.anyatsal.chefsboutique.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import by.anyatsal.chefsboutique.R
import by.anyatsal.chefsboutique.activity.BaseActivity
import by.anyatsal.chefsboutique.adapters.RecipeSearchAdapter
import by.anyatsal.chefsboutique.data.RecipeSearchItem

class RecipeSearchFragment : Fragment() {

    companion object {
        private const val ARGS_RECIPES = "ARGS_RECIPES"

        fun newInstance(
            recipes: List<RecipeSearchItem>
        ): RecipeSearchFragment {
            val fragment = RecipeSearchFragment()
            val bundle = Bundle()
            bundle.run {
                putParcelableArrayList(ARGS_RECIPES, ArrayList(recipes))
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
        val recipes = arguments?.getParcelableArrayList<RecipeSearchItem>(ARGS_RECIPES)
            ?: throw IllegalArgumentException("Missing recipes argument")
        val adapter = RecipeSearchAdapter(context, recipes) { position ->
            showDetailsFragment(recipes, position)
        }
        rv.adapter = adapter

        return view
    }

    private fun showDetailsFragment(recipes: List<RecipeSearchItem>, position: Int) {
        val url = recipes[position].url
        if (url != null) {
            BaseActivity.changeFragment(context, WebViewFragment.newInstance(url))
        }
    }
}