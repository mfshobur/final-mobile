package com.D121211088.film

import android.app.Application
import com.D121211088.news.data.AppCointainer
import com.D121211088.news.data.DefaultAppContainer

class MyApplication: Application() {
    lateinit var container: AppCointainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}