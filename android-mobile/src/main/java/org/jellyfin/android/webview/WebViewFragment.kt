package org.jellyfin.android.webview

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_web_view.*
import org.jellyfin.android.R
import org.json.JSONObject

class WebViewFragment(val server: String) : Fragment(
	R.layout.fragment_web_view
) {
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		webview.apply {
			settings.apply {
				@SuppressLint("SetJavaScriptEnabled")
				javaScriptEnabled = true
				domStorageEnabled = true
				mediaPlaybackRequiresUserGesture = false
				overScrollMode = WebView.OVER_SCROLL_NEVER
			}

			webViewClient = JellyfinClient(this@WebViewFragment)

			addJavascriptInterface(NativeShell(context), "Native_Shell")

			loadUrl(server)
		}
	}
}

class NativeShell(private val context: Context) {
	@JavascriptInterface
	fun getDeviceInformation(): String {
		return JSONObject().apply {
			put("deviceId", "test")
			put("deviceName", "Test device")
			put("appName", "Test app")
			put("appVersion", "1.0")
		}.toString()
	}

	fun enableFullscreen() {
		TODO()
	}

	fun disableFullscreen() {
		TODO()
	}

	fun openUrl(url: String, target: String) {
		TODO()
	}

	fun updateMediaSession(options: String) {
		TODO()
	}

	fun hideMediaSession() {
		TODO()
	}

	fun downloadFile(url: String) {
		TODO()
	}

	fun getPlugins() {
		TODO()
	}
}
