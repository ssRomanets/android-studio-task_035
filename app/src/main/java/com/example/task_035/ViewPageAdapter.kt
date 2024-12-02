package com.example.task_035

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPageAdapter(
    fragment: FragmentActivity,
    private val viewPagerList: MutableList<OnMainFragmentModel>
) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return viewPagerList.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = ViewPageFragment()
        fragment.arguments = bundleOf("vp" to viewPagerList[position])
        return fragment
    }
}