package com.example.internetshop.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.internetshop.databinding.ActivityAuthenticationBinding
import com.example.internetshop.domain.data.repository.LoginRepository
import com.example.internetshop.presentation.InternetshopApplication
import com.example.internetshop.presentation.viewModel.AuthenticationActivityViewModel
import javax.inject.Inject

class AuthenticationActivity : AppCompatActivity() {
    var viewModel: AuthenticationActivityViewModel? = null

    @Inject
    lateinit var loginRepository: LoginRepository

//    @Inject
//    lateinit var viewModelProviderFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        (applicationContext as InternetshopApplication).appComponent.inject(this)

        binding.login.setText("mor_2314")
        binding.password.setText("83r5^_")
        val myButton = binding.button

//        viewModel = ViewModelProvider(
//            this,
//            viewModelProviderFactory
//        ).get(AuthenticationActivityViewModel::class.java)
        myButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewModel?.tokenResult?.observe(this, Observer {
                Toast.makeText(this, it.token, Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            })
            viewModel?.textResult?.observe(this, Observer {
                binding.textError.text = it
                binding.progressBar.visibility = View.INVISIBLE
            })
            viewModel?.getToken(binding.login.text.toString(),binding.password.text.toString())
        }
    }
}