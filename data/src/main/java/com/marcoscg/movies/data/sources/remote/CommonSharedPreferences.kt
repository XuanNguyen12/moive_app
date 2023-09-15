/*
 * Created by CuongNV on 2/3/23, 1:53 PM
 * Copyright (c) by Begamob.com 2023 . All rights reserved.
 * Last modified 2/3/23, 8:54 AM
 */

package com.marcoscg.movies.data.sources.remote

import android.content.Context
import android.content.SharedPreferences


class CommonSharedPreferences {
    private var sharedPreferences: SharedPreferences? = null

    companion object {
        const val SHARED_PREFERENCES_NAME = "movie_app_sf"

        private lateinit var instance: CommonSharedPreferences

        fun init(context: Context) {
            instance = CommonSharedPreferences()
            instance.sharedPreferences = context
                .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        }

        @Synchronized
        fun getInstance(context: Context? = null): CommonSharedPreferences {
            if (!this::instance.isInitialized) {
                instance = CommonSharedPreferences()
                if (context != null) {
                    init(context)
                }
                return instance
            }
            return instance
        }
    }

    fun getSharedPreferences() =
        instance.sharedPreferences


    fun checkKey(key: String): Boolean? {
        return getSharedPreferences()?.contains(key)
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean =
        getSharedPreferences()?.getBoolean(key, defaultValue) ?: defaultValue

    fun putBoolean(key: String, value: Boolean) {
        val editor = getSharedPreferences()?.edit()
        editor?.putBoolean(key, value)
        editor?.apply()
    }

    fun getInt(key: String, defaultValue: Int): Int =
        getSharedPreferences()?.getInt(key, defaultValue) ?: defaultValue

    fun putInt(key: String, value: Int) {
        val editor = getSharedPreferences()?.edit()
        editor?.putInt(key, value)
        editor?.apply()
    }

    fun getLong(key: String, defaultValue: Long): Long =
        getSharedPreferences()?.getLong(key, defaultValue) ?: defaultValue

    fun putLong(key: String, value: Long) {
        val editor = getSharedPreferences()?.edit()
        editor?.putLong(key, value)
        editor?.apply()
    }

    fun getString(key: String, defaultValue: String? = ""): String =
        getSharedPreferences()?.getString(key, defaultValue) ?: ""

    fun putString(key: String, value: String) {
        val editor = getSharedPreferences()?.edit()
        editor?.putString(key, value)
        editor?.apply()
    }

    fun removeKey(key: String) {
        getSharedPreferences()?.edit()?.remove(key)?.apply()
    }

    fun setTokenUser(token: String) {
        putString("user_token", token)
    }

    fun getTokenUser(): String = getString("user_token", "")

    fun removeToken() {
        putString("user_token", "")
    }


}