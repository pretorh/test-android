package com.pretorh.myapplication

import android.app.DownloadManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_fragment2.*

class Fragment2 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fragment2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text.text = arguments?.getInt("arg2").toString()

        btn_download.setOnClickListener { startDownload() }
    }

    private fun startDownload() {
        val nonNullContext = context ?: return
        val downloadManager = ContextCompat.getSystemService(nonNullContext, DownloadManager::class.java) ?: return

        val uri = Uri.parse("https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_5mb.mp4")
        val request = DownloadManager.Request(uri)
        downloadManager.enqueue(request)
    }
}
