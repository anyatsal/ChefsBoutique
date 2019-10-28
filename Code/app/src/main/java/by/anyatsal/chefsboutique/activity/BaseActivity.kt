package by.anyatsal.chefsboutique.activity

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.anyatsal.chefsboutique.R

open class BaseActivity: AppCompatActivity(), View.OnClickListener {

    companion object {
        fun changeFragment(context: Context?, fragment: Fragment) {
            val ft = (context as BaseActivity).supportFragmentManager.beginTransaction()
            ft.replace(R.id.container, fragment)
            ft.addToBackStack(null)
            ft.commit()
        }
    }

    override fun onClick(v: View?) {
    }

    val progressBar by lazy {
        ProgressBar(this)
    }

    fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        if (progressBar.visibility == View.VISIBLE)
            progressBar.visibility = View.GONE
    }

    public override fun onStop() {
        super.onStop()
        hideProgressBar()
    }
}