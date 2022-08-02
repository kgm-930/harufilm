package com.example.todayfilm

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import org.json.JSONObject

object MyPreference {
    val sp_name = "my_sp_storage"

    fun write(context: Context, key:String, value:String) {
        val sp : SharedPreferences = context.getSharedPreferences(sp_name, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString(key, value)
        editor.apply()
//        Log.d("test:", "저장됨")
    }

    fun read(context: Context, key:String) : String{
        val sp : SharedPreferences = context.getSharedPreferences(sp_name, Context.MODE_PRIVATE)
        return sp.getString(key, "") ?:""
    }


}