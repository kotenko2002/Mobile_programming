package com.example.lab7

import android.R
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab7.adapters.ClientAdapter
import com.example.lab7.data.Client
import com.example.lab7.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var _binding: FragmentListBinding
    private var _listener: OnDataPassListener? = null
    private lateinit var _clients: ArrayList<Client>
    private lateinit var filteredClients: ArrayList<Client>

    interface OnDataPassListener {
        fun openDetailFragment(client: Client)
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
            _clients = it.getParcelableArrayList<Client>(ARG_CLIENTS) ?: ArrayList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater)
        val view = _binding.root

        filteredClients = ArrayList(_clients)

        val recyclerView: RecyclerView = _binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = ClientAdapter(filteredClients) { client ->
            _listener?.openDetailFragment(client)
        }
        recyclerView.adapter = adapter

        setupFilterSpinners(adapter)

        val itemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecoration)

        updateEmptyViewVisibility()

        return view
    }

    override fun onDetach() {
        super.onDetach()
        _listener = null
    }

    companion object {
        private const val ARG_CLIENTS = "clients"

        fun newInstance(stringList: ArrayList<Client>): ListFragment {
            val fragment = ListFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_CLIENTS, stringList)
            fragment.arguments = args
            return fragment
        }
    }

    private fun setupFilterSpinners(adapter: ClientAdapter) {
        val ageOptions = arrayOf("Всі", "Менше 18", "18-25", "26-35", "36-45", "46+")
        val genderOptions = arrayOf("Всі", "Чоловік", "Жінка")

        _binding.ageSpinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, ageOptions)
        _binding.genderSpinner.adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, genderOptions)

        _binding.ageSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                filterClients(adapter)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        })

        _binding.genderSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                filterClients(adapter)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        })
    }

    private fun filterClients(adapter: ClientAdapter) {
        val selectedAge = _binding.ageSpinner.selectedItem.toString()
        val selectedGender = _binding.genderSpinner.selectedItem.toString()

        filteredClients.clear()
        filteredClients.addAll(_clients.filter { client ->
            (selectedAge == "Всі" || client.ageCategory == selectedAge) &&
                    (selectedGender == "Всі" || client.gender == selectedGender)
        })

        adapter.notifyDataSetChanged()
        updateEmptyViewVisibility()
    }

    private fun updateEmptyViewVisibility() {
        if (filteredClients.isEmpty()) {
            _binding.recyclerView.visibility = View.GONE
            _binding.emptyView.visibility = View.VISIBLE
        } else {
            _binding.recyclerView.visibility = View.VISIBLE
            _binding.emptyView.visibility = View.GONE
        }
    }
}
