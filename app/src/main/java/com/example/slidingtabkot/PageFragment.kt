package com.example.slidingtabkot

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.Fragment


class PageFragment : Fragment() {
    private var mPage = 0
    private var mUrl: String? = null
    var awebview: WebView? = null
    var progressBar: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPage = requireArguments().getInt(ARG_PAGE)
        mUrl = requireArguments().getString(ARG_URL)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_page, container, false)

//        ProgressBar progressBar = view.findViewById(R.id.progressbar);
        val webView = view.findViewById<WebView>(R.id.mWebview)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                view.loadUrl(request.url.toString())
                return true
            }
        }
        webView.loadUrl(mUrl!!)
        return view
    }

    companion object {
        const val ARG_PAGE = "ARG_PAGE"
        const val ARG_URL = "ARG_URL"
        fun newInstance(page: Int, url: String?): PageFragment {
            val args = Bundle()
            args.putInt(ARG_PAGE, page)
            args.putString(ARG_URL, url)
            val fragment = PageFragment()
            fragment.arguments = args
            return fragment
        }
    }
}