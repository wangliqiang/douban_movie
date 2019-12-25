package com.app.douban_movie.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.app.douban_movie.R
import com.app.douban_movie.ui.fragments.hot.ComingSoonFragment
import com.app.douban_movie.ui.fragments.hot.InTheatersFragment
import kotlinx.android.synthetic.main.hot_fragment.*

class HotFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hot_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val titles = listOf("正在热映", "即将上映")
        hot_tabLayout.setupWithViewPager(hot_viewPager)
        hot_viewPager.adapter = object :
            FragmentPagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int): Fragment =
                when (position) {
                    0 -> InTheatersFragment()
                    else -> ComingSoonFragment()
                }

            override fun getCount(): Int = 2

            override fun getPageTitle(position: Int): CharSequence =
                titles[position]

        }
    }

}
