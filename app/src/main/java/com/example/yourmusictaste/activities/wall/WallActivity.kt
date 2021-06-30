package com.example.yourmusictaste.activities.wall

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.yourmusictaste.R
import com.example.yourmusictaste.activities.api.LastFmClient
import com.example.yourmusictaste.activities.api.RecentTracks
import com.example.yourmusictaste.activities.components.RecentTrackRecyclerAdapter
import com.example.yourmusictaste.activities.details.SongActivity
import com.example.yourmusictaste.activities.rank.RankActivity
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WallActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var usernameLogged: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wall)

        linearLayoutManager = LinearLayoutManager(this)


        usernameLogged = intent.getStringExtra("username")!!

        val usernameLoggedView: TextView = findViewById(R.id.username_logged)
        usernameLoggedView.text = usernameLogged

        val avatar: ImageView = findViewById(R.id.user_avatar)
        Picasso.get().load(intent.getStringExtra("avatar")).transform(CropCircleTransformation())
            .into(avatar);

        val trackListView: RecyclerView = findViewById(R.id.trackListView)
        trackListView.layoutManager = linearLayoutManager

        val chartBtn: ImageButton = findViewById(R.id.chartBtn)
        val rankActivity = Intent(this, RankActivity::class.java)
        rankActivity.putExtra("username", usernameLogged)

        val refreshLayout = findViewById<SwipeRefreshLayout>(R.id.swiperefresh)

        chartBtn.setOnClickListener {
            startActivity(rankActivity)
        }

        refreshLayout.setOnRefreshListener {
            refreshLayout.isRefreshing = true
            fillRecentTrackList(trackListView)
            refreshLayout.isRefreshing = false
        }

        fillRecentTrackList(trackListView)

    }

    private fun fillRecentTrackList(trackListView: RecyclerView) {
        val call = LastFmClient.service.getRecentTracks(usernameLogged, 20)
        val songDetailsActivity = Intent(this, SongActivity::class.java)

        call.enqueue(object : Callback<RecentTracks> {
            override fun onFailure(call: Call<RecentTracks>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<RecentTracks>, response: Response<RecentTracks>) {
                if (response.isSuccessful) {
                    trackListView.adapter = RecentTrackRecyclerAdapter(
                        response.body()!!.recenttracks.track
                    ) {
                        songDetailsActivity.putExtra("artist", it.artist.text)
                        songDetailsActivity.putExtra("track", it.name)
                        startActivity(songDetailsActivity)
                    }
                }
            }

        })
    }
}
