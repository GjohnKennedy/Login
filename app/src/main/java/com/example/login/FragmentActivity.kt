package com.example.login

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_layout.*

class FragmentActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)


        setSupportActionBar(toolbar)
        addFragment(HomeFragment.newInstance())
        bottomNavigation.show(1)
        bottomNavigation.add(MeowBottomNavigation.Model(0, R.drawable.notification))
        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_baseline_home_24))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_settings))



        bottomNavigation.setOnClickMenuListener {
            when (it.id) {
                0 -> {
                    replaceFragment(NotificationFragment())
                }
                1 -> {
                    replaceFragment(HomeFragment())
                }
                2 -> {
                    replaceFragment(SettingsFragment())
                }

                else -> {
                    replaceFragment(HomeFragment())
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer, fragment).commit()
//            .addToBackStack(Fragment::class.java.simpleName).commit()
    }

    private fun addFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.fragmentContainer, fragment).commit()
//            .addToBackStack(Fragment::class.java.simpleName).commit()

    }

    override fun onBackPressed() {

        Log.i("find","BackMethod")
        var navHost = supportFragmentManager.findFragmentById(R.id.home_menu)

        if(navHost==findViewById(R.id.home_menu))
        {
            super.onBackPressed()
            finish()
        }
        else
        {
            navHost = supportFragmentManager.findFragmentById(R.id.home_menu)
        }
//        navHost?.let { navFragment ->
//            navFragment.childFragmentManager.primaryNavigationFragment?.let { fragment ->
//                if (fragment is HomeFragment) {
//                    finish()
//                } else {
//                    super.onBackPressed()
//                }
//
//            }
//        }
    }
}