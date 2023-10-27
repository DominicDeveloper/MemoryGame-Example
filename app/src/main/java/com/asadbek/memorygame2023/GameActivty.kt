package com.asadbek.memorygame2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.asadbek.memorygame2023.databinding.ActivityGameActivtyBinding
import java.lang.Exception

class GameActivty : AppCompatActivity() {
    var minute = 0
    var secund = 60
    var record = 0
    val check_anim = arrayListOf(false,false,false,false,false,false,false,false,false,false,false,false,false)
    val imageIndex = arrayOfNulls<Int>(2)
    val idRasm = arrayOfNulls<Int>(2)
    var checkOpenCards = 0
    var nothing = 0
    var animationdoing = false
    lateinit var binding: ActivityGameActivtyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameActivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.img1.setOnClickListener {
            cardClick(binding.img1,R.drawable.tank,0)
        }

        binding.img2.setOnClickListener {
            cardClick(binding.img2,R.drawable.vehicle,1)
        }

        binding.img3.setOnClickListener {
            cardClick(binding.img3,R.drawable.ak,2)
        }

        binding.img4.setOnClickListener {
            cardClick(binding.img4,R.drawable.knife,3)
        }

        binding.img5.setOnClickListener {
            cardClick(binding.img5,R.drawable.hacking,4)
        }

        binding.img6.setOnClickListener {
            cardClick(binding.img6,R.drawable.knife,5)
        }

        binding.img7.setOnClickListener {
            cardClick(binding.img7,R.drawable.vehicle,6)
        }

        binding.img8.setOnClickListener {
            cardClick(binding.img8,R.drawable.pc,7)
        }

        binding.img9.setOnClickListener {
            cardClick(binding.img9,R.drawable.ak,8)
        }
        binding.img10.setOnClickListener{
            cardClick(binding.img10,R.drawable.pc,9)
        }
        binding.img11.setOnClickListener {
            cardClick(binding.img11,R.drawable.tank,10)
        }
        binding.img12.setOnClickListener {
            cardClick(binding.img12,R.drawable.hacking,11)
        }

        Time().start()

    }



    fun cardClick(imageView: ImageView,rasm: Int,index:Int){
        if (!animationdoing){
            if (check_anim[index] == false){
                opening(imageView,rasm,index)
            }else{
                closing(imageView,rasm,index)
            }
        }
    }
    // ochilish animatsiyasi uchun funktsiya
    fun opening(imageView: ImageView,rasm: Int,index: Int){
        val anim = AnimationUtils.loadAnimation(this,R.anim.scale)
        imageView.startAnimation(anim)
        anim.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                val anim_2 = AnimationUtils.loadAnimation(this@GameActivty,R.anim.anim_1)

                imageView.startAnimation(anim_2)
                imageView.setImageResource(rasm)
                anim_2.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationStart(p0: Animation?) {
                        animationdoing = true
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        check_anim[index] = true
                        imageIndex[checkOpenCards] = index
                        idRasm[checkOpenCards] = rasm
                        checkOpenCards++

                        if (checkOpenCards == 2){
                            if (idRasm[0] == idRasm[1]){
                                record++
                                correctAnswer(findImg(imageIndex[0]),findImg(imageIndex[1]))
                            }else{
                                closing(findImg(imageIndex[0]), -1, imageIndex[0]!!)
                                closing(findImg(imageIndex[1]), -1, imageIndex[1]!!)
                            }
                        }
                        animationdoing = false
                    }

                    override fun onAnimationRepeat(p0: Animation?) {

                    }
                })
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })

    }
    // yopilish animatsiyasi uchun funktsiya
    fun closing(imageView: ImageView,rasm: Int,index: Int){
        val animation = AnimationUtils.loadAnimation(this,R.anim.scale)
        imageView.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                animationdoing = true
            }

            override fun onAnimationEnd(p0: Animation?) {
                val anim2 = AnimationUtils.loadAnimation(this@GameActivty,R.anim.anim_1)
                imageView.startAnimation(anim2)
                imageView.setImageResource(R.drawable.gamefon)
                anim2.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationStart(p0: Animation?) {

                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        animationdoing = false
                    }

                    override fun onAnimationRepeat(p0: Animation?) {

                    }
                })

                check_anim[index] = false
                checkOpenCards--
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })
    }
    // rasmni(imageviewni) tanlab olish uchun
    fun findImg(index: Int?):ImageView{
        var imageView:ImageView? = null

        when(index){
            0 -> imageView = binding.img1
            1 -> imageView = binding.img2
            2 -> imageView = binding.img3
            3 -> imageView = binding.img4
            4 -> imageView = binding.img5
            5 -> imageView = binding.img6
            6 -> imageView = binding.img7
            7 -> imageView = binding.img8
            8 -> imageView = binding.img9
            9 -> imageView = binding.img10
            10 -> imageView = binding.img11
            11 -> imageView = binding.img12
        }
        return imageView!!
    }
    // vaqt minut uchun
    fun minute(){
        if (secund == 0 && minute != 0){
            minute--
            secund = 60
        }
    }
    fun correctAnswer(imageView1: ImageView,imageView2: ImageView){
        val animremoving = AnimationUtils.loadAnimation(this,R.anim.absenting_anim)

        imageView1.startAnimation(animremoving)
        imageView2.startAnimation(animremoving)

        // objectdan implement olish uchun: alt + enter bosiladi
        animremoving.setAnimationListener(object :Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                animationdoing = true
            }

            override fun onAnimationEnd(p0: Animation?) {
                findImg(imageIndex[0]).visibility = View.INVISIBLE
                checkOpenCards--
                findImg(imageIndex[1]).visibility = View.INVISIBLE
                checkOpenCards--
                animationdoing = false
                toWin()
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })

    }
    private fun toWin() {
        if (record == 6){
            win_game()
        }else{
            nothing += 1
        }
    }
    private fun win_game() {
        val i = Intent(this,MyRecordActivity::class.java)
        val record = Record(record.toString())
        i.putExtra("rc",record)
        startActivity(i)
    }
    private fun stopTime(){
        binding.img1.isEnabled = false
        binding.img2.isEnabled = false
        binding.img3.isEnabled = false
        binding.img4.isEnabled = false
        binding.img5.isEnabled = false
        binding.img6.isEnabled = false
        binding.img7.isEnabled = false
        binding.img8.isEnabled = false
        binding.img9.isEnabled = false
        binding.img10.isEnabled = false
        binding.img11.isEnabled = false
        binding.img12.isEnabled = false
        val i = Intent(this,MyRecordActivity::class.java)
        val record = Record(record.toString())
        i.putExtra("rc",record)
        startActivity(i)
    }
    fun take_error(){
        if (minute == 0 && secund == 0){
            try {
                binding.timeText.text = "Time End"
                binding.timeText.setTextColor(ContextCompat.getColor(applicationContext,R.color.red))
                stopTime()
            }catch (e:Exception){
                Toast.makeText(this, "Time end!", Toast.LENGTH_SHORT).show()
            }
        }
    }
   inner class Time:Thread(){
       override fun run() {
           super.run()

           while (minute != 0 || secund != 0){
               secund--
               minute()
               binding.timeText.text = "$minute:$secund"
               sleep(1000)
           }
           take_error()
       }
   }


}