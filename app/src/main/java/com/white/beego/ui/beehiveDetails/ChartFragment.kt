package com.white.beego.ui.beehiveDetails

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.white.beego.databinding.FragmentChartBinding
import com.white.beego.models.BeehiveResponse
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ChartFragment() : Fragment() {

    private var _binding: FragmentChartBinding? = null
    private val binding get() = _binding!!
    private var beehive: BeehiveResponse? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentChartBinding.inflate(inflater, container, false)

        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBeehiveData()
        setInitialChart()


    }

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setInitialChart() {
        var html = ""
        html += createChartIframe("6480d397-f045-48a6-87ee-210564ca0c49")
        html += createSecondChartIframe("647ed955-f045-4926-8bee-210564498f6b")

        binding.webView.loadData(html, "text/html; video/avc", "utf-8")

        binding.webView.webViewClient = WebViewClient()
        binding.webView.webChromeClient = WebChromeClient()
        binding.webView.settings.setPluginState(WebSettings.PluginState.ON)
        binding.webView.settings.setPluginState(WebSettings.PluginState.ON_DEMAND)
        binding.webView.settings.safeBrowsingEnabled = false
        binding.webView.settings.domStorageEnabled = true
        binding.webView.settings.javaScriptEnabled = true
    }

    private fun createChartIframe(chartId: String): String {
        val sdf = SimpleDateFormat("yyyy-M-dd")
        val currentDate = sdf.format(Date())

        val deviceId = beehive!!.deviceID

        val iframe =
            "<iframe width=\"100%\" height=\"500px\" src=\"https://charts.mongodb.com/charts-project-0-duzrk/embed/charts?" +
                    "id=$chartId" +
                    "&filter={deviceID: '$deviceId'}" +
                    "&theme=light&autoRefresh=true&maxDataAge=10&showTitleAndDesc=false&scalingWidth=scale&scalingHeight=fixed\" title=\"Beehive chart\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>\n"

        // val iframe = "<iframe style=\"background: #F1F5F4;border: none;border-radius: 2px;box-shadow: 0 2px 10px 0 rgba(70, 76, 79, .2);width: 100vw;height: 100vh;\"  src=\"https://charts.mongodb.com/charts-project-0-duzrk/embed/dashboards?id=6467d5e1-a807-4c9a-83e6-9918daae0694&theme=light&autoRefresh=true&maxDataAge=3600&showTitleAndDesc=false&scalingWidth=fixed&scalingHeight=fixed\"></iframe>"

        return iframe
    }

    private fun createSecondChartIframe(chartId: String): String {
        val sdf = SimpleDateFormat("yyyy-M-dd")
        val currentDate = sdf.format(Date())

        val deviceID = beehive!!.deviceID

        val iframe =
            "<iframe width=\"100%\" height=\"500px\" src=\"https://charts.mongodb.com/charts-project-0-duzrk/embed/charts?" +
                    "id=$chartId" +
                    "&filter={deviceID: '$deviceID'}" +
                    "&theme=light&autoRefresh=true&maxDataAge=10&showTitleAndDesc=false&scalingWidth=scale&scalingHeight=fixed\" title=\"Beehive chart\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>\n"

        // val iframe = "<iframe style=\"background: #F1F5F4;border: none;border-radius: 2px;box-shadow: 0 2px 10px 0 rgba(70, 76, 79, .2);width: 100vw;height: 100vh;\"  src=\"https://charts.mongodb.com/charts-project-0-duzrk/embed/dashboards?id=6467d5e1-a807-4c9a-83e6-9918daae0694&theme=light&autoRefresh=true&maxDataAge=3600&showTitleAndDesc=false&scalingWidth=fixed&scalingHeight=fixed\"></iframe>"

        return iframe
    }

    private fun setBeehiveData() {
        val jsonBeehive = arguments?.getString("chart")
        if (jsonBeehive != null) {
            beehive = Gson().fromJson(jsonBeehive, BeehiveResponse::class.java)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}