package by.anyatsal.chefsboutique.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import by.anyatsal.chefsboutique.R
import by.anyatsal.chefsboutique.data.Recipe
import coil.api.load

class RecipeFragment : Fragment() {

    companion object {
        private const val ARG_RECIPE = "ARG_RECIPE"

        fun newInstance(recipe: Recipe): RecipeFragment {
            val fragment = RecipeFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_RECIPE, recipe)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //if (arguments?.getParcelable<Recipe>(ARG_RECIPE)?.url != null) {
            //return inflater.inflate(R.layout.fragment_recipe_web_view, container, false)
        //}
        return inflater.inflate(R.layout.frament_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Recipe>(ARG_RECIPE)?.run {

            /*if (url.isNullOrEmpty()) {
                view.findViewById<TextView>(R.id.recipe_name).text = name
                view.findViewById<TextView>(R.id.recipe_ingredients).text = ingredients
                view.findViewById<TextView>(R.id.recipe_description).text = description
                view.findViewById<TextView>(R.id.recipe_category).text = category
                view.findViewById<ImageView>(R.id.img_recipe).load(imageRes)
            }
            else {
                //val webView = view.findViewById(R.id.recipe_web_view)
                //webView.loadUrl(url)
            }*/
        }
    }
}