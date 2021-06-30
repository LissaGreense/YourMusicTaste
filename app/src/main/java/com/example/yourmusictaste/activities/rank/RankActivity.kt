package com.example.yourmusictaste.activities.rank

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yourmusictaste.R
import com.example.yourmusictaste.activities.api.LastFmClient
import com.example.yourmusictaste.activities.api.RankArtist
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RankActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)

        val usernameLogged = intent.getStringExtra("username")!!

        val chart = findViewById<PieChart>(R.id.chart)
        setUpChart(chart)
        fillChart(usernameLogged, chart)

    }

    private fun setUpChart(chart: PieChart) {
        chart.description.text = ""
        chart.setHoleColor(0)
        chart.legend.isEnabled = false
    }

    private fun fillChart(
        usernameLogged: String,
        chart: PieChart
    ) {
        val entries: MutableList<PieEntry> = mutableListOf()
        val call = LastFmClient.service.getTopArtists(usernameLogged, 5)

        call.enqueue(object : Callback<RankArtist> {
            override fun onFailure(call: Call<RankArtist>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<RankArtist>, response: Response<RankArtist>) {
                if (response.isSuccessful) {
                    response.body()?.topartists?.artist?.forEach {
                        entries.add(PieEntry(it.playcount.toFloat(), it.name))
                    }
                    val dataSet = PieDataSet(entries, "Top Artists")
                    dataSet.colors = listOf(
                        Color.parseColor("#f87060"),
                        Color.parseColor("#f87e6f"),
                        Color.parseColor("#f98c7f"),
                        Color.parseColor("#fa9a8f"),
                        Color.parseColor("#faa99f")
                    )
                    chart.data = PieData(dataSet)
                    chart.invalidate()
                }
            }

        })
    }
}