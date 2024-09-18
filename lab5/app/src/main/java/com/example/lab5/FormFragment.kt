package com.example.lab5

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lab5.databinding.FragmentFormBinding
import java.util.UUID

class FormFragment : Fragment() {
    private lateinit var _binding: FragmentFormBinding
    private var _listener: OnDataPassListener? = null
    private var _recipes: ArrayList<Recipe>? = null

    interface OnDataPassListener {
        fun add(newValue: Recipe)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnDataPassListener) {
            _listener = context
        } else {
            throw RuntimeException("$context must implement OnDataPassListener")
        }
    }

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
        _binding = FragmentFormBinding.inflate(inflater)
        val view = _binding.root

        _binding.addRecipeButton.setOnClickListener {
            val recipe = Recipe(
                id = UUID.randomUUID().toString(),
                title = _binding.titleInput.text.toString(),
                shortDescription = _binding.shortDescriptionInput.text.toString(),
                ingredients = _binding.ingredientsInput.text.split(","),
                instructions = _binding.instructionsInput.text.split("."),
            )

            _listener?.add(recipe)
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        _listener = null
    }

    companion object {
        private const val ARG_RECIPES= "recipes"

        fun newInstance(stringList: ArrayList<Recipe>): FormFragment {
            val fragment = FormFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_RECIPES, stringList);
            fragment.arguments = args
            return fragment
        }
    }
}
