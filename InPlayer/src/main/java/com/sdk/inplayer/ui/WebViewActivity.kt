package com.sdk.inplayer.ui

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_web_view.*


class WebViewActivity : AppCompatActivity() {
    
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
        setContentView(com.sdk.inplayer.R.layout.content_web_view)
        
        if (!intent.hasExtra(WebViewUrl))
            throw RuntimeException("WebViewActivity must contain WebViewActivity.URL bundle key")
        
        val url = intent.getStringExtra(WebViewUrl)
        
        web_view.settings.userAgentString = USER_AGENT
        web_view.settings.javaScriptEnabled = true
        web_view.webViewClient = SocialWebViewClient()
        if (url != null) {
            web_view.loadUrl(url)
        }
    }
    
    
    inner class SocialWebViewClient : WebViewClient() {
        
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            Log.v(TAG, "URL is $url")
            return true
        }
        
    }
    
}
