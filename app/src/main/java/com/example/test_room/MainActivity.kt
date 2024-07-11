package com.example.test_room

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.test_room.databinding.ActivityMainBinding
import com.example.test_room.room.DefaltDatabase
import com.example.test_room.ui.dashboard.DashboardFragment
import com.example.test_room.ui.home.HomeFragment
import com.example.test_room.ui.notifications.NotificationsFragment
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var defaltDatabase: DefaltDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewPager()
        navigationSelected()

        /*
                val navView: BottomNavigationView = binding.navView
                //val navController = findNavController(R.id.nav_host_fragment_activity_main)
                val navController = findNavController(R.id.vp_viewpager_main)
                val appBarConfiguration = AppBarConfiguration(
                    setOf(
                        R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
                    )
                )
                setupActionBarWithNavController(navController, appBarConfiguration)
                navView.setupWithNavController(navController)
        */


    }


    private fun initViewPager() {
        var viewPager2Adapter = ViewPager2Adapter(this)
        viewPager2Adapter.addFragment(HomeFragment())
        viewPager2Adapter.addFragment(DashboardFragment())
        viewPager2Adapter.addFragment(NotificationsFragment())



        binding.vpViewpagerMain.apply {

            adapter = viewPager2Adapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.d("fragment main" ,"fragment ${position}")


                    binding.navView.menu.getItem(position).isChecked = true
                }
            }
            )

        }.setPageTransformer(ZoomOutPageTransformer())

    }


    private fun navigationSelected() {
        binding.navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    binding.vpViewpagerMain.currentItem = 0
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_dashboard -> {
                    binding.vpViewpagerMain.currentItem = 1
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_notifications -> {
                    binding.vpViewpagerMain.currentItem = 3
                    return@setOnItemSelectedListener true
                }

                else -> {
                    binding.vpViewpagerMain.currentItem = 4
                    return@setOnItemSelectedListener false
                }
            }
        }
    }


}