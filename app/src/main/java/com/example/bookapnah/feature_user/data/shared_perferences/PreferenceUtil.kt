package com.example.bookapnah.feature_user.data.shared_perferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import kotlin.reflect.KClass

class PreferenceUtil(context: Context) {
    private val mSharedPref: SharedPreferences

    init {
        var context = context
        mSharedPref = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    companion object {
        const val NAME: String = "NAME"
        const val LOGIN = "LOGIN"
        const val USER_DETAILS = "USER_DETAILS"

    }

    // **************GET NORMAL STRING  ***********************//
    var token: String
        get() = mSharedPref.getString(NAME, "token").toString()
        set(token) = mSharedPref.edit().putString(NAME, token).apply()


    var login: Boolean
        get() = mSharedPref.getBoolean(LOGIN, false)
        set(login) = mSharedPref.edit().putBoolean(LOGIN, login).apply()

    /**
     * Saves object into the Preferences.
     *
     * @param `object` Object of model class (of type [T]) to save
     * @param key Key with which Shared preferences to
     **/
    fun <T> put(`object`: T, key: String) {
        //Convert object to JSON String.
        val jsonString = GsonBuilder().create().toJson(`object`)
        //Save that String in SharedPreferences
        mSharedPref.edit().putString(key, jsonString).apply()
    }

    /**
     * Used to retrieve object from the Preferences.
     *
     * @param key Shared Preference key with which object was saved.
     **/
    fun <  T :Any > get(c:KClass<T>,  key: String):T? {
        //We read JSON String which was saved.
        val value = mSharedPref.getString(key, null)
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type Class < T >" is used to cast.
        return GsonBuilder().create().fromJson(value, c.java)
    }


}