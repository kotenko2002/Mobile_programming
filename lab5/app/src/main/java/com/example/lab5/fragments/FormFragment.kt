package com.example.lab5.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.lab5.models.Recipe
import com.example.lab5.R
import com.example.lab5.databinding.FragmentFormBinding

class FormFragment : Fragment() {
    private lateinit var _binding: FragmentFormBinding
    private var _listener: OnDataPassListener? = null
    private var _recipes: ArrayList<Recipe>? = null

    interface OnDataPassListener {
        fun onDataPass(newValue: Recipe)
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

        val tempText = view.findViewById<TextView>(R.id.temp_text)
        tempText.text = _recipes?.joinToString(",") { it.title }

        view.findViewById<Button>(R.id.pass_data_button_1).setOnClickListener {
            _listener?.onDataPass(Recipe("5B1", "5B2",  listOf("1"), listOf("1")))
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
