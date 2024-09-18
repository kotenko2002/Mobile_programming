package com.example.lab5

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab5.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var _binding: FragmentDetailBinding
    private var _listener: OnDataPassListener? = null
    private var _recipe: Recipe? = null

    interface OnDataPassListener {
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
            _recipe = it.getParcelable(ARG_RECIPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater)
        val view = _binding.root

        //_binding.deleteRecipeButton.setOnClickListener {
        //_listener?.delete(_recipe?.id ?: "")
        //}

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