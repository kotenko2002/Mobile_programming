package com.example.lab9.fragments.api_posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.lab9.R
import com.example.lab9.api.PostViewModel
import com.example.lab9.databinding.FragmentAllPostsBinding

class AllPostsFragment : Fragment() {
    private lateinit var _binding: FragmentAllPostsBinding
    private val postViewModel: PostViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllPostsBinding.inflate(inflater)

        val paginationLayout: LinearLayout = _binding.root.findViewById(R.id.pagination_buttons)

        for (i in 0..9) {
            val fetchDataButton = Button(requireContext()).apply {
                text = (i + 1).toString()
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(4, 0, 4, 0)
                }
                minWidth = 0
                minimumWidth = 0
                val paddingValue = if (text.length == 1) 38 else 32
                setPadding(paddingValue, 8, paddingValue, 8)
            }

            fetchDataButton.setOnClickListener {
                postViewModel.getPosts(i)
            }

            paginationLayout.addView(fetchDataButton)
        }

        return _binding.root
    }
}
