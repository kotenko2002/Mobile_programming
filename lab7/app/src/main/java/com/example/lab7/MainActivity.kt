package com.example.lab7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab7.data.Client
import com.example.lab7.data.ClientTempData

class MainActivity : AppCompatActivity(),
    FormFragment.OnDataPassListener,
    ListFragment.OnDataPassListener,
    DetailFragment.OnDataPassListener
{
    private val _clients = ClientTempData.clients;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        renderFormAndList()
    }

    override fun add(newValue: Client) {
        _clients.add(newValue)
        renderFormAndList()
    }

    override fun openDetailFragment(client: Client) {
        val formFragment = DetailFragment.newInstance(client)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.firstFragment, formFragment)
            .remove(supportFragmentManager.findFragmentById(R.id.secondFragment) ?: return)
            .addToBackStack(null)
            .commit()
    }

    override fun back() {
        supportFragmentManager.popBackStack()
    }

    override fun saveChanges(client: Client) {
        val index = _clients.indexOfFirst { it.id == client.id }
        if (index != -1) {
            _clients[index] = client
            renderFormAndList()
        }
    }

    override fun delete(clientId: String) {
        val clientToRemove = _clients.find { it.id == clientId }
        if (clientToRemove != null) {
            _clients.remove(clientToRemove)
            renderFormAndList()
        }
    }

    private fun renderFormAndList() {
        val formFragment = FormFragment.newInstance()
        val listFragment = ListFragment.newInstance(_clients)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.firstFragment, formFragment)
            .replace(R.id.secondFragment, listFragment)
            .addToBackStack(null)
            .commit()
    }
}