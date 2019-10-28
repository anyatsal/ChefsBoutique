package by.anyatsal.chefsboutique.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

object Utils {

    fun showMessage(view: View?, message: String) {
        Snackbar.make(view!!, message, Snackbar.LENGTH_LONG).show()
    }

    fun showMessageIfEmpty(view: View?) {

        Snackbar.make(view!!, "Please, fill in all the fields", Snackbar.LENGTH_LONG).show()
    }
}