package com.kma.movies.ui.splash

import android.os.Bundle
import com.kma.movies.base.BaseActivity
import com.kma.movies.common.navigation.navigateToHome

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateToHome()
    }

}