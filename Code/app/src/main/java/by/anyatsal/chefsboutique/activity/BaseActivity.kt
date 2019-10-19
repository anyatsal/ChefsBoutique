package by.anyatsal.chefsboutique.activity

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

open class BaseActivity: AppCompatActivity() {
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

    fun showMessage(view: View?, message: String) {
        Snackbar.make(view!!, message, Snackbar.LENGTH_LONG).show()
    }

    fun showMessageIfEmpty(view: View?) {
        Snackbar.make(view!!, "Please, fill in all the fields", Snackbar.LENGTH_LONG).show()
    }
}