package com.geeks.noteapp.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.geeks.noteapp.R
import com.geeks.noteapp.databinding.ActivityMainBinding
import com.geeks.noteapp.utils.Preference

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    val preference = Preference()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
        }

    private fun initialize() {

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        val navController = navHostFragment?.navController ?: return
        preference.unit(this)

        if (!preference.isFirstVisit) {
            val navOptions = NavOptions.Builder().setPopUpTo(R.id.onBoardFragment, true).build()
            navController.navigate(R.id.notesFragment, null, navOptions)
        }
    }
}