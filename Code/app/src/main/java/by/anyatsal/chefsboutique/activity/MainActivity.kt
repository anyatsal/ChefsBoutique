package by.anyatsal.chefsboutique.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.anyatsal.chefsboutique.R
import by.anyatsal.chefsboutique.data.Recipe
import by.anyatsal.chefsboutique.fragment.RecipeListFragment
import by.anyatsal.chefsboutique.list.RecipesAdapter
import by.anyatsal.chefsboutique.list.RecipesViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var recipesViewModel: RecipesViewModel
    private val newRecipesActivityRequestCode = 1

    companion object {

        fun getLaunchIntent(from: Context) = Intent(from, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

        fun changeFragment(context: Context?, fragment: Fragment) {
            val ft = (context as MainActivity).supportFragmentManager.beginTransaction()
            ft.replace(R.id.container, fragment)
            ft.addToBackStack(null)
            ft.commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        isLogged()

        val recyclerView = findViewById<RecyclerView>(R.id.rv_recipe_list)
        val adapter = RecipesAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        recipesViewModel = ViewModelProvider(this).get(RecipesViewModel::class.java)
        recipesViewModel.recipes.observe(this, Observer { recipes ->
            recipes?.let { adapter.setRecipes(recipes) }
        })
        //setSupportActionBar(bottom_app_bar)

        fab.setOnClickListener(this)
        bottom_app_bar.setNavigationOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        isLogged()
    }

    private fun isLogged() {
        mAuth.currentUser?.uid?.let {
            changeFragment(this, RecipeListFragment.newInstance())
        } ?: startActivity(LoginActivity.getLaunchIntent(this))
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        if (fm.backStackEntryCount > 1) {
            fm.popBackStack()
        } else {
            finish()
        }
    }

    override fun onStop() {
        fab.visibility = View.GONE
        super.onStop()
    }

    override fun onResume() {
        fab.visibility = View.VISIBLE
        super.onResume()
    }

    override fun onClick(v: View?) {
        when (v) {
            fab -> {
                val intent = Intent(this, CreateRecipeActivity::class.java)
                startActivityForResult(intent, newRecipesActivityRequestCode)
            }
            else -> {
                startActivity(SearchActivity.getLaunchIntent(this))
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newRecipesActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val recipe = intentData?.getParcelableExtra<Recipe>(CreateRecipeActivity.EXTRA_REPLY)
            if (recipe != null) {
                recipesViewModel.insert(recipe)
            }
        } else {

        }
    }
}
