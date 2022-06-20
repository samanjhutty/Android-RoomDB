package com.o7solutions.roomdbexample.prefrances

import android.content.Context
import android.content.SharedPreferences

class PrefManager(context: Context){
    private var name="rajacool"
    private val pref:SharedPreferences=context.getSharedPreferences(name,Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor=pref.edit()
    var PASSWORD="pswd"


    fun setPassword(x:String){
        editor.putString(PASSWORD,x)
        editor.apply()
        editor.commit()
    }

    fun getPassword():String?{

        return pref.getString(PASSWORD,"")
    }





}