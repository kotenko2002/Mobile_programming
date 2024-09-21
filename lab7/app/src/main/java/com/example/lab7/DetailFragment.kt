package com.example.lab7

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab7.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var _binding: FragmentDetailBinding
    private var _listener: OnDataPassListener? = null
    private lateinit var _recipe: Recipe

    interface OnDataPassListener {
        fun back()
        fun saveChanges(recipe: Recipe)
        fun delete(recipeId: String)
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
            _recipe = it.getParcelable(ARG_RECIPE) ?: Recipe()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater)
        val view = _binding.root

        _binding.titleInput.setText(_recipe.title)
        _binding.shortDescriptionInput.setText(_recipe.shortDescription)
        _binding.ingredientsInput.setText(_recipe.ingredients.joinToString(separator = ","))
        _binding.instructionsInput.setText(_recipe.instructions.joinToString(separator = ","))

        _binding.backButton.setOnClickListener {
            _listener?.back()
        }
        _binding.saveChangesButton.setOnClickListener {
            val recipe = Recipe(
                id = _recipe.id,
                title = _binding.titleInput.text.toString(),
                shortDescription = _binding.shortDescriptionInput.text.toString(),
                ingredients = _binding.ingredientsInput.text.split(","),
                instructions = _binding.instructionsInput.text.split("."),
            )

            _listener?.saveChanges(recipe)
        }
        _binding.deleteButton.setOnClickListener {
            _listener?.delete(_recipe.id)
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        _listener = null
    }

    companion object {
        private const val ARG_RECIPE = "recipe"

        fun newInstance(recipe: Recipe): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_RECIPE, recipe)
            fragment.arguments = args
            return fragment
        }
    }
}