package com.asadbek.memorygame2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.asadbek.memorygame2023.databinding.ActivityMyRecordBinding

class MyRecordActivity : AppCompatActivity() {
    lateinit var binding: ActivityMyRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        check()

        binding.restsrtTheGame.setOnClickListener {
            val intent = Intent(this,GameActivty::class.java)
            startActivity(intent)
        }
    }

    private fun check() {
        val getRecord:Record = intent.getSerializableExtra("rc") as Record

        if (getRecord.record != "6"){
            binding.recordMe.text = "Your Record: ${getRecord.record}"
        }else{
            binding.recordMe.isAllCaps = true
            binding.recordMe.text = "You are winner: ${getRecord.record}"
            val animation = AnimationUtils.loadAnimation(this,R.anim.winner)
            binding.rm.startAnimation(animation)
        }

    }
}