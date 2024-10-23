package com.example.lab9.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.lab9.api.PostViewModel
import com.example.lab9.databinding.FragmentPostsBinding
import com.example.lab9.fragments.api_posts.ApiPostsFragment
import com.example.lab9.fragments.db_posts.DbPostsFragment

class PostsFragment : Fragment() {
    private lateinit var _binding: FragmentPostsBinding
    private var currentPosition: Int = 0
    private val postViewModel: PostViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostsBinding.inflate(inflater)
        val view = _binding.root

        _binding.viewPager.adapter = object : FragmentPagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int): Fragment {
                return when (position) {
                    0 -> DbPostsFragment()
                    else -> ApiPostsFragment()
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

        _binding.viewPager.addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                currentPosition = position
            }

            override fun onPageScrollStateChanged(state: Int) {
                if (state == androidx.viewpager.widget.ViewPager.SCROLL_STATE_IDLE && currentPosition == 1) {
                    postViewModel.getPosts(0)
                }
            }
        })

        return view
    }
}
