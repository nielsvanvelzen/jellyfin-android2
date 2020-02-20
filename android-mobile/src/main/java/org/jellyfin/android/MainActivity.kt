package org.jellyfin.android

import android.os.Bundle
import android.webkit.WebView
import androidx.fragment.app.FragmentActivity
import org.jellyfin.android.webview.WebViewFragment

class MainActivity : FragmentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		// Enable remote-debugging
		WebView.setWebContentsDebuggingEnabled(true)

		// Create fragment
		val webViewFragment = WebViewFragment("https://demo.jellyfin.org/stable")

		supportFragmentManager
			.beginTransaction()
			.replace(android.R.id.content, webViewFragment)
			.commit()
	}
}
