package com.dicoding.medikan.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.medikan.R
import com.dicoding.medikan.databinding.ActivitySplashScreenBinding
import com.dicoding.medikan.viewmodel.UserViewModel
import com.dicoding.medikan.viewmodel.base.ViewModelFactory
import java.util.Timer
import kotlin.concurrent.schedule

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private var _binding: ActivitySplashScreenBinding? = null
    val binding get() = _binding!!
    private lateinit var viewModel: ViewModelFactory
    private val userViewModel: UserViewModel by viewModels { viewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelFactory.getInstance(this)

        userViewModel.apply {
            getSessionUser().observe(this@SplashScreenActivity) {
                Timer("OpenLogin", false).schedule(3000) {
                    if (it.isLogin){
                        startActivity(Intent(this@SplashScreenActivity, HomeActivity::class.java))
                    } else {
                        startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
                    }
                    finish()
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}