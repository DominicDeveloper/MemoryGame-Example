package com.asadbek.memorygame2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asadbek.memorygame2023.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startGame.setOnClickListener {
            val intent = Intent(this,GameActivty::class.java)
            startActivity(intent)
        }

        binding.restartGame.setOnClickListener {
            val intent = Intent(this,GameActivty::class.java)
            startActivity(intent)
        }

    }
}