package com.sania.airliftcasestudy.ui.utility

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.util.*


class SharedPrefManager private constructor(context: Context) {

    private val sharedpreferences: SharedPreferences


    init {
        sharedpreferences = context.getSharedPreferences(context.packageName,
                Context.MODE_PRIVATE)
    }

    fun clearPreferences() {
        val editor = sharedpreferences.edit()
        editor.clear()
        editor.commit()
    }


    fun setBooleanForKey(key: String, value: Boolean?) {
        val editor = sharedpreferences.edit()
        editor.putBoolean(key, value!!)
        editor.commit()
    }

    fun setStringForKey(value: String, key: String) {
        val editor = sharedpreferences.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun setIntegerForKey(value: Int, key: String) {
        val editor = sharedpreferences.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun setLongForKey(value: Long, key: String) {
        val editor = sharedpreferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }


    fun getBooleanByKey(key: String): Boolean {
        return if (sharedpreferences.contains(key)) {
            sharedpreferences.getBoolean(key, false)
        } else false
    }

    fun getBooleanByKeyForMusic(key: String): Boolean {
        return if (sharedpreferences.contains(key)) {
            sharedpreferences.getBoolean(key, true)
        } else true
    }

    fun getStringByKey(key: String): String? {
        return if (sharedpreferences.contains(key)) {
            sharedpreferences.getString(key, "")
        } else null
    }

    fun getIntegerByKey(key: String): Int {
        return if (sharedpreferences.contains(key)) {
            sharedpreferences.getInt(key, 0)
        } else 0
    }

    fun getIntegerByKey(key: String, defaultValue: Int): Int {
        return if (sharedpreferences.contains(key)) {
            sharedpreferences.getInt(key, defaultValue)
        } else defaultValue
    }

    fun getLongByKey(key: String): Long {
        return if (sharedpreferences.contains(key)) {
            sharedpreferences.getLong(key, 0)
        } else 0
    }

    fun saveCollectionTOSharedPref(collection: Any, key: String) {
        val value = Gson().toJson(collection)
        setStringForKey(value, key)
    }

    fun <T> getCollectionFromSharedPref(key: String, toCast: Class<T>?): ArrayList<T>? {
        val value = getStringByKey(key)
        val gson = Gson()
        return if (value != null && value != "") {
            val collectionType = TypeToken.getParameterized(ArrayList::class.java, toCast).type
            gson.fromJson(value, collectionType)
        } else {
            null
        }
    }

    fun saveObjectInSharedPref(obj: Any, key: String) {
        val gson = Gson()
        val json = gson.toJson(obj)
        setStringForKey(json, key)
    }

    fun getObjectFromSharedPref(key: String, toCast: Class<*>): Any? {
        val gson = Gson()
        val json = getStringByKey(key)
        return if (json != null && json != "") {

            try {
                var newjson = json.substring(1, json.length - 1).replace("\\r","").replace("\\n", "").replace("\\t", "").replace("\\", "")
                gson.fromJson(newjson,
                        toCast)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        } else {
            null
        }
    }

    companion object {
        private var mInstance: SharedPrefManager? = null

        fun getInstance(context: Context): SharedPrefManager {
            if (mInstance == null)
                mInstance = SharedPrefManager(context.applicationContext)
            return mInstance!!
        }
    }


}
