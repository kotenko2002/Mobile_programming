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
    private var stringList: ArrayList<String>? = null

    interface OnDataPassListener {
        fun onDataPass(newValue: String)
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
            stringList = it.getStringArrayList(ARG_STRING_LIST)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_form, container, false)

        view.findViewById<TextView>(R.id.temp_text).text = stringList?.joinToString(",")

        view.findViewById<Button>(R.id.pass_data_button_1).setOnClickListener {
            listener?.onDataPass("New Item")
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        private const val ARG_STRING_LIST = "string_list"

        fun newInstance(stringList: ArrayList<String>): FormFragment {
            val fragment = FormFragment()
            val args = Bundle()
            args.putStringArrayList(ARG_STRING_LIST, stringList)
            fragment.arguments = args
            return fragment
        }
    }
}