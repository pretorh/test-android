package com.pretorh.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG by lazy { this.javaClass.simpleName }
    private lateinit var model: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "MainActivity.onCreate")
        model = ViewModelProviders.of(this).get(MainViewModel::class.java)

        setTextViewFromSimpleModelField()
        setTextFieldFromLiveDataAndChangeOnButtonClicks()
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
}
