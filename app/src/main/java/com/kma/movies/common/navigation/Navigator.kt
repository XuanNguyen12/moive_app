package com.kma.movies.common.navigation

import android.app.Activity
import android.content.Intent
import com.kma.movies.ui.home.HomeActivity

fun Activity.navigateToHome() {
    this.startActivity(Intent(this, HomeActivity::class.java))
    this.finish()
}