package com.pretorh.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.pretorh.myapplication.core.observeEvent
import com.pretorh.myapplication.core.observeEventList
import kotlinx.android.synthetic.main.activity_main_fragment.*

class MainFragment : Fragment() {
    private val TAG by lazy { this.javaClass.simpleName }
    private lateinit var model: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.activity_main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "MainFragment.onCreate")
        model = ViewModelProviders.of(this).get(MainViewModel::class.java)

        setTextViewFromSimpleModelField()
        setTextFieldFromLiveDataAndChangeOnButtonClicks()
        setButtonToLoadFromNetwork()
        setupNavigation()
    }

    private fun setTextViewFromSimpleModelField() {
        textView.text = model.r.toString()
    }

    private fun setTextFieldFromLiveDataAndChangeOnButtonClicks() {
        model.currentName.observe(this, Observer<String> { name -> textView2.text = name })
        btn_set_name.setOnClickListener {
            val name = editText.text.toString()
            model.setName(name)
        }

        model.firstName.observe(this, Observer<String> { name -> textViewFirstNameFromRepository.text = name })

        model.firstName.observe(this, Observer<String> { name ->
            val message = "String observer: $name"
            if (checkbox_enable_snackbar.isChecked) {
                Snackbar.make(textViewFirstNameFromRepository, message, Snackbar.LENGTH_SHORT).show()
            }
            Log.d(TAG, message)
        })
        model.firstNameEvent.observeEvent(this, { name ->
            val message = "Event observer: $name"
            if (checkbox_enable_snackbar.isChecked) {
                Toast.makeText(context!!, message, Toast.LENGTH_SHORT).show()
            }
            Log.d(TAG, message)
        })

        model.randomNumberGenerator.observe(this, Observer<Int> {
            Log.d(TAG, "observed random number $it")
        })
        model.randomNumberEvents.observeEventList(this, {
            val items = it.joinToString(",")
            Log.d(TAG, "observed random numbers (list) $items")
        })

        button_delete.setOnClickListener { model.clearFirstName() }
    }

    private fun setButtonToLoadFromNetwork() {
        button_load.setOnClickListener { model.loadFromNetwork() }
    }

    private fun setupNavigation() {
        button_navigate.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_fragment2))
        button_navigate3.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_to_fragment3))
    }
}
