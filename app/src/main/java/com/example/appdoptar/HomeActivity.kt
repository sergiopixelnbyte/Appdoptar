package com.example.appdoptar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth


enum class ProviderType{
    BASIC
}

class HomeActivity : AppCompatActivity() {
    private var emailTextView: TextView?= null
    private var providerTextView : TextView?= null
    private var signOutButton: Button ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        emailTextView = findViewById(R.id.emailTextView)
        providerTextView = findViewById(R.id.providerTextView)
        signOutButton = findViewById(R.id.signOutButton)

        setContentView(R.layout.activity_home)

        //Setup

        val bundle: Bundle? = intent.extras
        val email: String? = bundle?.getString("email")
        val provider: String? = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")
    }

    private fun setup(email: String, provider: String){
        title = "Inicio Appdoptar"
        emailTextView?.text = email
        providerTextView?.text = provider

        signOutButton?.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }
}