package com.pretorh.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_fragment3.*

class Fragment3 : Fragment() {
    private lateinit var viewModel: Fragment3ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment3, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text3.setOnClickListener { text3.findNavController().navigate(R.id.action_fragment3_to_fragment4) }
        viewModel = ViewModelProviders.of(this).get(Fragment3ViewModel::class.java)
        viewModel.getText().observe(this, Observer { s ->
            text3.text = s
        })
        viewModel.getText2().observe(this, Observer { s ->
            text3_2.text = s
        })
    }
}
