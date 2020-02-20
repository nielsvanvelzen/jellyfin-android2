package org.jellyfin.android.webview

import android.webkit.MimeTypeMap
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.webkit.WebViewClientCompat
import java.io.FileNotFoundException
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset

class JellyfinClient(private val fragment: WebViewFragment) : WebViewClientCompat() {
	override fun shouldOverrideUrlLoading(
		view: WebView,
		request: WebResourceRequest
	) = false

	override fun shouldInterceptRequest(
		view: WebView,
		request: WebResourceRequest
	): WebResourceResponse? {
		if (request.url.host.equals("jellyfin.local", true)) {
			val mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
				MimeTypeMap.getFileExtensionFromUrl(request.url.path)
			) ?: "text/plain"
			val path = request.url.path ?: "/index.html"

			return WebResourceResponse(
				mime,
				"UTF-8",
				fragment.context!!.assets.open(path.trimStart('/'))
			)
		}

		//todo best check ever lol
		if (request.url.path!!.endsWith("index.html", true)) {
			try {
				val url = URL(request.url.toString())
				val connection = url.openConnection() as HttpURLConnection

				val response = WebResourceResponse(
					connection.contentType.split(';').first(), // strip charset from content type
					connection.getHeaderField("encoding") ?: "UTF-8",
					connection.responseCode,
					connection.responseMessage,
					connection.headerFields.mapValues { it.value.first() },
					connection.inputStream.readBytes().toString(Charset.defaultCharset()).replace(
						"<head>",
						"""<head><script type="text/javascript" defer src="//jellyfin.local/init.js"></script>"""
					).byteInputStream()
				)

				return response
			} catch (err: FileNotFoundException) {
				return WebResourceResponse(
					"text/html",
					"UTF-8",
					404,
					"Not Found",
					emptyMap(),
					"".byteInputStream()
				)
			}
		}

		return null
	}

//	override fun onPageFinished(view: WebView, url: String) {
//		super.onPageFinished(view, url)
//
//		val script =
//			fragment.context!!.assets.open("init.js").readBytes().toString(Charset.defaultCharset())
//
//		view.evaluateJavascript(script) {}
//	}
}
