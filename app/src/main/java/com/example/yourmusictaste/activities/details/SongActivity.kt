package com.example.yourmusictaste.activities.details

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yourmusictaste.R
import com.example.yourmusictaste.activities.api.LastFmClient
import com.example.yourmusictaste.activities.api.SimilarSongs
import com.example.yourmusictaste.activities.api.TrackInfo
import com.example.yourmusictaste.activities.components.SimilarSongsRecyclerAdapter
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SongActivity: AppCompatActivity() {
    private var trackName: String = ""
    private var artistName: String = ""
    private lateinit var artist: TextView
    private lateinit var title: TextView
    private lateinit var album: TextView
    private lateinit var albumImage: ImageView
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        setLayoutElements()
        linearLayoutManager = LinearLayoutManager(this)
        trackName = intent.getStringExtra("track")!!
        artistName = intent.getStringExtra("artist")!!

        fillSongInfo()

        val similarSongsListView: RecyclerView = findViewById(R.id.similarSongList)
        similarSongsListView.layoutManager = linearLayoutManager

        val call = LastFmClient.service.getSimilarTracks(artistName, trackName, 20)
        call.enqueue(object : Callback<SimilarSongs>{
            override fun onFailure(call: Call<SimilarSongs>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<SimilarSongs>, response: Response<SimilarSongs>) {
                if (response.isSuccessful) {
                    similarSongsListView.adapter = SimilarSongsRecyclerAdapter(response.body()!!.similartracks.track)
                }
            }

        })
    }

    private fun fillSongInfo() {
        val call = LastFmClient.service.getTrackInfo(artistName, trackName)

        call.enqueue(object : Callback<TrackInfo> {
            override fun onFailure(call: Call<TrackInfo>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<TrackInfo>, response: Response<TrackInfo>) {
                if (response.isSuccessful && response.body()?.track != null) {
                    artist.text = response.body()!!.track.artist.name
                    title.text = response.body()!!.track.name
                    album.text = response.body()!!.track.album.title
                    var url = ""
                    response.body()!!.track.album.image.forEach {
                        if (it.size == "large") {
                            url = it.text
                        }
                    }
                    Picasso.get().load(url).into(albumImage);
                }
            }
        })
    }

    private fun setLayoutElements() {
        artist = findViewById(R.id.songArtist)
        title = findViewById(R.id.songTitle)
        album = findViewById(R.id.songAlbum)
        albumImage = findViewById(R.id.albumLogo)
    }
}