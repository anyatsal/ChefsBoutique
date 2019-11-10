package by.anyatsal.chefsboutique.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import by.anyatsal.chefsboutique.BuildConfig
import by.anyatsal.chefsboutique.R
import by.anyatsal.chefsboutique.activity.BaseActivity.Companion.changeFragment
import by.anyatsal.chefsboutique.api.RecipeService
import by.anyatsal.chefsboutique.utils.Utils.showMessage
import by.anyatsal.chefsboutique.utils.Utils.showMessageIfEmpty
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() =
            SearchFragment()
    }

    private var disposable: Disposable? = null

    private val recipeService by lazy {
        RecipeService.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchButton: Button = view.findViewById(R.id.btn_search)
        searchButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when {
            recipe_name.text.isNullOrEmpty() -> showMessageIfEmpty(v)
            else -> startSearch(v, recipe_name.text.toString())
        }
    }

    private fun startSearch(v: View?, name: String) {
        progressbar.visibility = View.VISIBLE
        disposable = recipeService.getRecipeList(BuildConfig.ApiKey, name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if (result.recipes.isNullOrEmpty()) {
                        showMessage(v, "The result set is empty")
                        progressbar.visibility = View.GONE
                        return@subscribe
                    } else {
                        changeFragment(
                            context,
                            RecipeSearchFragment.newInstance(result.recipes!!)
                        )
                    }
                },
                { showMessage(v, "The result set is empty") }
            )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
