package com.example.test_room

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPager2Adapter(fragmentActivity: FragmentActivity) :FragmentStateAdapter(fragmentActivity){
    var fragments : ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int {
     return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        Log.d("fragment" ,"fragment ${position}")
        return fragments[position]
        Log.d("fragment1" ,"fragment ${position}")

    }


    
    fun addFragment(fragment: Fragment){
        fragments.add(fragment)
        notifyItemChanged(fragments.size-1)
    }
    fun removeFragement(){
        fragments.removeLast()
        notifyItemRemoved(fragments.size)
    }

}