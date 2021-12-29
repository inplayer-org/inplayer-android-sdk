package com.sdk.inplayer.ui

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.sdk.inplayer.databinding.ContentWebViewBinding


class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ContentWebViewBinding

    private val TAG = "WebViewActivity"
    
    var MyUA =
        "Mozilla/5.0 (Linux; Android 4.4.4; One Build/KTU84L.H4) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36"
    
    
    var USER_AGENT =
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36"
    
    
    companion object {
        
        val WebViewUrl = "WebViewActivity.URL"
        
        fun startActivity(url: String, context: Context): Intent {
            return Intent(context, WebViewActivity::class.java).apply {
                putExtra(WebViewUrl, url)
                flags = FLAG_ACTIVITY_NEW_TASK
            }
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ContentWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        if (!intent.hasExtra(WebViewUrl))
            throw RuntimeException("WebViewActivity must contain WebViewActivity.URL bundle key")
        
        val url = intent.getStringExtra(WebViewUrl)
        
        binding.webView.settings.userAgentString = USER_AGENT
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = SocialWebViewClient()
        if (url != null) {
            binding.webView.loadUrl(url)
        }
    }
    
    
    inner class SocialWebViewClient : WebViewClient() {
        
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            Log.v(TAG, "URL is $url")
            return true
        }
        
    }
    
}
