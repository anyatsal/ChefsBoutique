package by.anyatsal.chefsboutique.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.anyatsal.chefsboutique.R
import by.anyatsal.chefsboutique.utils.Utils.showMessage
import by.anyatsal.chefsboutique.utils.Utils.showMessageIfEmpty
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private lateinit var auth: FirebaseAuth

    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, LoginActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        button_login.setOnClickListener(this)
        register.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            button_login -> {
                when {
                    email.text.isNullOrEmpty() ||
                            password.text.isNullOrEmpty() -> showMessageIfEmpty(v)
                    !android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches() -> showMessage(
                        v,
                        "Please, enter your real email"
                    )
                    else -> signIn(v, email.text.toString(), password.text.toString())
                }
            }
            register -> createAccount()
        }
    }

    private fun createAccount() {
        val intentToSignUp = Intent(this, SignUpActivity::class.java)
        startActivity(intentToSignUp)
        finish()
    }

    private fun signIn(view: View?, email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                showProgressBar()
                Intent(this, MainActivity::class.java)
                startActivity(MainActivity.getLaunchIntent(this))
            } else {
                showMessage(view, "Error with login, please, try later")
            }
        }
    }
}