package com.example.lab5

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class FormFragment : Fragment() {
    private var listener: OnDataPassListener? = null
    private var _recipes: ArrayList<Recipe>? = null

    interface OnDataPassListener {
        fun onDataPass(newValue: Recipe)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnDataPassListener) {
            listener = context
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
        val view = inflater.inflate(R.layout.fragment_form, container, false)

        val tempText = view.findViewById<TextView>(R.id.temp_text)
        tempText.text = _recipes?.joinToString(",") { it.name }

        view.findViewById<Button>(R.id.pass_data_button_1).setOnClickListener {
            listener?.onDataPass(Recipe("123","123","123"))
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
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
