package com.example.myapp

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class WebviewActivity : AppCompatActivity() {

    val TAG = "WebviewActivity"

    // var webView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val webView = findViewById<WebView>(R.id.webview1)

        // 声明 WebSettings子类
        val webSettings = webView.getSettings()
        // 如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true)
        // 缩放操作
        webSettings.setSupportZoom(true)   // 支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true)  // 设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false)   // 隐藏原生的缩放控件
        // 其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK)  // 关闭webview中缓存
        webSettings.setAllowFileAccess(true)    // 设置可以访问文件
        webSettings.setDefaultTextEncodingName("utf-8")   // 设置编码格式

        // webView?.loadUrl("https://ww.baidu.com]")  // 加载百度首页
        webView?.loadUrl("https://m.baidu.com/s?word=$searchText")  // 加载百度结果页

        webView!!.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                // 设置不用系统浏览器打开,直接显示在当前Webview
                Log.i(TAG, "shouldOverrideUrlLoading :" + url)

                if (url.startsWith("http")) {
                    view.loadUrl(url)
                }
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                // 设定加载开始的操作
                // 我们可以设定一个loading的页面，告诉用户网页在加载中。
            }

            override fun onPageCommitVisible(view: WebView?, url: String?) {
                super.onPageCommitVisible(view, url)
                // 设定可视阶段的操作
                // webview可视阶段调用，指后端返回数据，webview开始渲染时回调，当前一般还是白屏状态。
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                // 设定加载结束的操作
                // 这个时候的页面已经加载完成，核心的JS也已经运行，可以关闭loading条
            }

            override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
                super.doUpdateVisitedHistory(view, url, isReload)
                // webview历史变更的回调，告诉webview历史记录更新。
                Log.i(TAG, "doUpdateVisitedHistory :" + url)
            }

            override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
                super.onReceivedError(view, request, error)
            }

            override fun onReceivedHttpError(view: WebView, request: WebResourceRequest, errorResponse: WebResourceResponse) {
                super.onReceivedHttpError(view, request, errorResponse)
            }

            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                super.onReceivedSslError(view, handler, error)
            }
        }

        webView!!.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, progress: Int) {
                super.onProgressChanged(view, progress)
                Log.i(TAG, "onProgressChanged :" + progress)
            }

            override fun onReceivedTitle(view: WebView?, title: String) {
                super.onReceivedTitle(view, title)
                Log.i(TAG, "onReceivedTitle :" + title)
            }
        }

    }

    // webView 后退
    fun goBack(view: View) {
        val webView = findViewById<WebView>(R.id.webview1)
        if (webView.canGoBack()) {
            webView.goBack(); //似乎goBack()方法有问题
        } else {
            webView.destroy()
            finish();
        }
    }

    // webView 前进
    fun goForward(view: View) {
        val webView = findViewById<WebView>(R.id.webview1)
        if(webView.canGoForward())
            webView.goForward()
        else
            Toast.makeText(this, "已经到顶了哦", Toast.LENGTH_SHORT).show()
    }

    // webView 关闭
    fun webClose(view: View){
        val webView = findViewById<WebView>(R.id.webview1)
        webView.destroy()
        finish()
    }

    companion object{
        var searchText: String? = null
    }
}