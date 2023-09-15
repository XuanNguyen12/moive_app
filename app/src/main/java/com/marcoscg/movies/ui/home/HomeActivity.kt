package com.marcoscg.movies.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.marcoscg.movies.R
import com.marcoscg.movies.base.BaseActivity
import com.marcoscg.movies.common.navigation.setupWithNavController
import com.marcoscg.movies.common.utils.gone
import com.marcoscg.movies.common.utils.visible
import com.marcoscg.movies.databinding.ActivityHomeBinding


class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            setupBottomNavigation()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigation()
    }

    @SuppressLint("RestrictedApi")
    private fun setupBottomNavigation() {
        val controller = binding.bottomNavigation.setupWithNavController(
            listOf(
                R.navigation.navigation_popular,
                R.navigation.navigation_favorite,
                R.navigation.navigation_account
            ),
            supportFragmentManager,
            R.id.nav_host_container,
            intent
        )
//         Whenever the selected controller changes, setup the action bar.
        controller.observe(this, Observer { navController ->
            menuItem?.isVisible = navController.graph.displayName.contains("navigation_popular")
            navController.currentDestination
            setupActionBarWithNavController(navController)
        })

        currentNavController = controller
    }

    private var menuItem: MenuItem? = null
    override fun onSupportNavigateUp() = currentNavController?.value?.navigateUp() ?: false
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuItem = menu?.findItem(R.id.toolbar_search)
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.toolbar_search) {
            val action = PopularFragmentDirections.navigateToSearch()
            val  navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_container);
            (navHostFragment?.childFragmentManager?.fragments?.last() as? PopularFragment)?.findNavController()?.navigate(action)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
