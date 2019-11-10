package by.anyatsal.chefsboutique.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import by.anyatsal.chefsboutique.R

class WebViewFragment: Fragment() {

    private lateinit var webView: WebView

    companion object {
        private const val URL = "URL"

        fun newInstance(url: String): WebViewFragment {
            val fragment = WebViewFragment()
            val bundle = Bundle()
            bundle.run {
                putString(URL, url)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_web_view, container, false)
        webView = view.findViewById(R.id.recipe_web_view)
        webView.webViewClient = WebViewClient()
        webView.loadUrl(arguments?.getString(URL))
        return view
    }
}