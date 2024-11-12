package com.example.controlwork.ui.followedLocations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.controlwork.databinding.FragmentFollowedLocationsBinding

class FollowedLocationsFragment : Fragment() {
    private var _binding: FragmentFollowedLocationsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val followedLocationsViewModel =
            ViewModelProvider(this).get(FollowedLocationsViewModel::class.java)

        _binding = FragmentFollowedLocationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textFollowedLocations
        followedLocationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}