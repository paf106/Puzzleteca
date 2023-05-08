package com.pablo.puzzleteca.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.pablo.puzzleteca.R
import com.pablo.puzzleteca.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavigationView = binding.bottomNavigationView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.puzzlesFragment,
                R.id.favouritesFragment,
                R.id.settingsFragment
            )
        )

        val navController = navHostFragment.navController

       /* setupActionBarWithNavController(navController, appBarConfiguration)*/

        // Sincronizacion del navController con nuesto bottom navigation
        bottomNavigationView.setupWithNavController(navController)
    }

}
