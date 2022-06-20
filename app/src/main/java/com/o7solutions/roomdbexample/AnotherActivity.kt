package com.o7solutions.roomdbexample

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.o7solutions.roomdbexample.databinding.ActivityAnotherBinding
import com.o7solutions.roomdbexample.prefrances.PrefManager

class AnotherActivity : AppCompatActivity() {
    lateinit var binding:ActivityAnotherBinding
    lateinit var myPref:PrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    binding=DataBindingUtil.setContentView(this,R.layout.activity_another)
        myPref= PrefManager(this)
        binding.btnStore.setOnClickListener {
            myPref.setPassword(binding.edPswd.text.toString())
            binding.layoutSave.visibility=View.GONE
            binding.layoutShow.visibility=View.VISIBLE
            Toast.makeText(this,"data store",Toast.LENGTH_SHORT).show()
        }
        binding.btnShow.setOnClickListener {

             val pswd=myPref.getPassword().toString()

            binding.tvShowPswd.setText(pswd)
            binding.layoutSave.visibility=View.VISIBLE

        }



    }
}