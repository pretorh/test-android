package com.pretorh.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG by lazy { this.javaClass.simpleName }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "MainActivity.onCreate")

        val model = ViewModelProviders.of(this).get(MainViewModel::class.java)

        textView.text = model.r.toString()

        model.currentName.observe(this, Observer<String> { name -> textView2.text = name })
    }
}
