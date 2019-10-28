package by.anyatsal.chefsboutique.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.anyatsal.chefsboutique.R
import by.anyatsal.chefsboutique.fragment.SearchFragment
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {

    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, SearchActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(bottom_app_bar)
        bottom_app_bar.setNavigationOnClickListener(this)

        changeFragment(this, SearchFragment.newInstance())
    }

    override fun onClick(v: View?) {
        onBackPressed()
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        if (fm.backStackEntryCount > 1) {
            fm.popBackStack()
        } else {
            startActivity(MainActivity.getLaunchIntent(this))
        }
    }
}