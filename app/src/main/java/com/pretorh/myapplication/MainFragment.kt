package com.pretorh.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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

        button_delete.setOnClickListener { model.clearFirstName() }
    }

    private fun setButtonToLoadFromNetwork() {
        button_load.setOnClickListener { model.loadFromNetwork() }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
