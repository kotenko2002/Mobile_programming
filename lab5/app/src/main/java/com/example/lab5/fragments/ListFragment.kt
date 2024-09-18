package com.example.lab5.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab5.adapters.RecipeAdapter
import com.example.lab5.Recipe
import com.example.lab5.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var _binding: FragmentListBinding
    private var _recipes: ArrayList<Recipe>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            _recipes = it.getParcelableArrayList<Recipe>(ARG_RECIPES)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater)
        val view = _binding.root

        val recyclerView: RecyclerView = _binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = _recipes?.let {
            RecipeAdapter(it) { recipe ->
                Log.d("FFF123", recipe.title)
            }
        }

        return view
    }

    companion object {
        private const val ARG_RECIPES= "recipes"

        fun newInstance(stringList: ArrayList<Recipe>): ListFragment {
            val fragment = ListFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_RECIPES, stringList);
            fragment.arguments = args
            return fragment
        }
    }
}
