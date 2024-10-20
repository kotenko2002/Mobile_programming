package com.example.lab9.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lab9.databinding.FragmentPostsBinding
import com.example.lab9.fragments.api_posts.AllPostsFragment
import com.example.lab9.fragments.db_posts.FavoritePostsFragment

class PostsFragment : Fragment() {
    private lateinit var _binding: FragmentPostsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostsBinding.inflate(inflater)
        val view = _binding.root

        _binding.viewPager.adapter = object : FragmentPagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int): Fragment {
                return when (position) {
                    0 -> FavoritePostsFragment()
                    else -> AllPostsFragment()
                }
            }

            override fun getCount(): Int = 2

            override fun getPageTitle(position: Int): CharSequence? {
                return when (position) {
                    0 -> "Улюблені пости"
                    else -> "Усі пости"
                }
            }
        }

        _binding.tabLayout.setupWithViewPager(_binding.viewPager)
        return view
    }
}