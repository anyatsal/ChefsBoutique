package by.anyatsal.chefsboutique.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.anyatsal.chefsboutique.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), View.OnClickListener {

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
                val intentToMain = Intent(this, MainActivity::class.java)
                startActivity(intentToMain)
            } else {
                showMessage(view, "${task.exception?.message}")
            }
        }
    }
}