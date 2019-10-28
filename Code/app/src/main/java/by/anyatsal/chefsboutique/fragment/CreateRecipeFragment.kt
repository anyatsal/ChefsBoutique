package by.anyatsal.chefsboutique.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.anyatsal.chefsboutique.R
import by.anyatsal.chefsboutique.data.DBHelper
import kotlinx.android.synthetic.main.fragment_create_recipe.*

class CreateRecipeFragment: Fragment(), View.OnClickListener {
    companion object {
        fun newInstance(recipeDBHelper: DBHelper) =
            CreateRecipeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        img_create_recipe.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            img_create_recipe -> {}

        }
    }
}
