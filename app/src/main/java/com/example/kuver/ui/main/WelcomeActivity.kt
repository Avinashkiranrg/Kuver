package com.example.kuver.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kuver.Utils.ActivityUtil
import com.example.kuver.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private var _binding: ActivityWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getStartedBtn .setOnClickListener {
            ActivityUtil.startActivity(this, LoginActivity::class.java,true)
        }
    }
}