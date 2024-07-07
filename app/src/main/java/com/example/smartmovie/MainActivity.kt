package com.example.smartmovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.smartmovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ExploreFragment())
            .commit()

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_explore -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ExploreFragment())
                        .commit()
                    true
                }
                R.id.navigation_play -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, PlayFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }

}
