package com.app.douban_movie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.app.douban_movie.R
import com.app.douban_movie.ui.fragments.FindFragment
import com.app.douban_movie.ui.fragments.HotFragment
import com.app.douban_movie.ui.fragments.MineFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeUI()
    }

    private fun initializeUI() {

        main_viewPager.adapter = object : FragmentPagerAdapter(
            supportFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        ) {
            override fun getItem(position: Int): Fragment =
                when (position) {
                    0 -> HotFragment()
                    1 -> FindFragment()
                    else -> MineFragment()
                }

            override fun getCount(): Int = 3

        }

        main_viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                main_bottomNavigationView.menu.getItem(position).isChecked = true
            }

        })

        main_bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_hot -> main_viewPager.currentItem = 0
                R.id.navigation_find -> main_viewPager.currentItem = 1
                R.id.navigation_mine -> main_viewPager.currentItem = 2
            }
            true
        }

    }
}