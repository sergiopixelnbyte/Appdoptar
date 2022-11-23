package com.example.appdoptar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {

    var signUpButton: Button?= null;
    var logInButton: Button?= null;
    var passwordEditText: EditText?= null;
    var emailEditText: EditText?= null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        signUpButton = findViewById(R.id.signOutButton)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        logInButton = findViewById(R.id.logInButton)
        //Setup
        setUp()
    }

    private fun setUp(){
        title = "Authentication"
        signUpButton?.setOnClickListener{
            if (emailEditText?.text!!.isNotEmpty() &&passwordEditText?.text!!.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
                        emailEditText?.text!!.toString(),
                        passwordEditText?.text!!.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            showHome(it?.result?.user?.email ?: "", ProviderType.BASIC)
                        }else{
                            showAlert()
                        }
                    }

            }
        }

        logInButton?.setOnClickListener{
            if (emailEditText?.text!!.isNotEmpty() &&passwordEditText?.text!!.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        emailEditText?.text!!.toString(),
                        passwordEditText?.text!!.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            showHome(it?.result?.user?.email ?: "", ProviderType.BASIC)
                        }else{
                            showAlert()
                        }
                    }

            }
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, HomeActivity::class.java).apply{
            putExtra("email", email)
            putExtra("provider", provider)
        }
        startActivity(homeIntent)
    }
}