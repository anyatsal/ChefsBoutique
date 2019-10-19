package by.anyatsal.chefsboutique.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import by.anyatsal.chefsboutique.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()

        button_sign_up.setOnClickListener(this)
        login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            login -> signIn()
            button_sign_up -> {
                when {
                    username.text.isNullOrEmpty()
                            || email.text.isNullOrEmpty()
                            || password.text.isNullOrEmpty()
                            || confirm_password.text.isNullOrEmpty() -> showMessageIfEmpty(v)
                    password.text.toString() != confirm_password.text.toString() -> showMessage(
                        v,
                        "Passwords don't match"
                    )
                    else -> verifyUser(v, email.text.toString(), password.text.toString())
                }
            }
        }
    }

    private fun signIn() {
        val intentToLogin = Intent(this, LoginActivity::class.java)
        startActivity(intentToLogin)
        finish()
    }

    private fun verifyUser(view: View?, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    showMessage(view, "Error with registration, please try again")
                }
                hideProgressBar()
            }
    }
}
