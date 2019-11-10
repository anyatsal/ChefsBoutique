package by.anyatsal.chefsboutique.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.anyatsal.chefsboutique.R
import by.anyatsal.chefsboutique.data.DBHelper
import by.anyatsal.chefsboutique.fragment.RecipeListFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var recipeDBHelper: DBHelper

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

        recipeDBHelper = DBHelper(this)

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
            changeFragment(this, RecipeListFragment.newInstance(recipeDBHelper.readAllRecipes()))
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
        when(v) {
            fab -> {
                startActivity(CreateRecipeActivity.getLaunchIntent(this))
            }
            else -> {
                startActivity(SearchActivity.getLaunchIntent(this))
            }
        }
    }
}
