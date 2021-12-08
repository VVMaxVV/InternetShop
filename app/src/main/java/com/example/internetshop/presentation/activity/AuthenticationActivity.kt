package com.example.internetshop.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.internetshop.databinding.ActivityAuthenticationBinding
import com.example.internetshop.presentation.ViewModel.AuthenticationActivityViewModel
import com.example.internetshop.presentation.ViewModelFactory

class AuthenticationActivity : AppCompatActivity() {
    var viewModel: AuthenticationActivityViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val myButton = binding.button
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory()
        ).get(AuthenticationActivityViewModel::class.java)
        myButton.setOnClickListener {
            binding.pb.visibility = View.VISIBLE
            viewModel?.tokenResult?.observe(this, Observer {
                Toast.makeText(this, it.tokenValue, Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                //val intent = Intent(this, ProductsActvity::class.java)
                startActivity(intent)
                finish()
            })
            viewModel?.textResult?.observe(this, Observer {
                binding.textError.text = it
                binding.pb.visibility = View.INVISIBLE
            })
            viewModel?.getToken(binding.login.text.toString(),binding.password.text.toString())
        }
    }
}