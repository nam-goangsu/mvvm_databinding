package com.example.test_room

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.test_room.databinding.ActivityMainBinding
import com.example.test_room.room.DefaltDatabase
import com.example.test_room.ui.dashboard.DashboardFragment
import com.example.test_room.ui.home.HomeFragment
import com.example.test_room.ui.map.MapsFragment
import com.example.test_room.ui.notifications.NotificationsFragment
import com.google.android.gms.maps.MapView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var defaltDatabase: DefaltDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewPager()
        navigationSelected()
        disableViewpager2swipe(binding.vpViewpagerMain, 3)

        binding.vpViewpagerMain.setNestedScrollingEnabled(true)
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
        viewPager2Adapter.addFragment(MapsFragment())



        binding.vpViewpagerMain.apply {

            adapter = viewPager2Adapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {


                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
//                    Log.d("fragment main" ,"fragment ${position}")
                    binding.navView.menu.getItem(position).isChecked = true

                    if (position == 3){
                        binding.backfragment.visibility = View.VISIBLE
                        binding.vpViewpagerMain.isUserInputEnabled =false
                    }else{
                        binding.backfragment.visibility = View.INVISIBLE
                        binding.vpViewpagerMain.isUserInputEnabled =true
                    }
                }
            }
            )

        }.setPageTransformer(ZoomOutPageTransformer())
        binding.backfragment.setOnClickListener {

            binding.vpViewpagerMain.currentItem = 2

        }


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
                    binding.vpViewpagerMain.currentItem = 2
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_maps -> {
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
/*    private var intercept = false
    private val slideBorder = 0.1

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                intercept = ev.x > wallpaperDesiredMinimumWidth * slideBorder && ev.x < wallpaperDesiredMinimumWidth * (1 - slideBorder)
            }
            MotionEvent.ACTION_MOVE -> {
                parent.requestDisallowInterceptTouchEvent(intercept)
            }
        }
        return super.dispatchTouchEvent(ev)
    }*/

    //requestDisallowInterceptTouchEvent()
    private fun disableViewpager2swipe(viewPager: ViewPager2, disabledPagePosition: Int) {
        val recyclerView = viewPager.getChildAt(0) as RecyclerView

        Log.d("test" ,viewPager.childCount.toString() + " " +viewPager.currentItem)

        recyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                if (viewPager.currentItem == disabledPagePosition) {
                    val targetView = rv.findChildViewUnder(e.x, e.y)
                    Log.d("test" , "ex ${e.x} ey ${e.y}")

                    targetView?.findViewById<View>(R.id.map)?.let {
                        val mapRect = IntArray(2)
                        it.getLocationOnScreen(mapRect)
                        val mapX = mapRect[0]
                        val mapY = mapRect[1]


                        if (e.rawX >= mapX && e.rawX <= mapX + it.width &&
                            e.rawY >= mapY && e.rawY <= mapY + it.height) {
                            return false // MapView 터치 이벤트는 허용

                        }
                    }

                    return true // 스와이프 막기
                }/*else{
                    binding.backfragment.visibility = View.INVISIBLE
                    binding.vpViewpagerMain.isUserInputEnabled = true
                    return false
                }
                binding.vpViewpagerMain.isUserInputEnabled = true*/
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

                Log.d("test" , "touch event ex ${e.x} ey ${e.y}")
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })
    }


}