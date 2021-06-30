package com.example.yourmusictaste.activities.components

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yourmusictaste.R
import com.example.yourmusictaste.activities.api.SimilarTrack
import com.squareup.picasso.Picasso

class SimilarSongsRecyclerAdapter(private val list: List<SimilarTrack>):
    RecyclerView.Adapter<SimilarSongViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarSongViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SimilarSongViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: SimilarSongViewHolder, position: Int) {
        val song = list[position]
        holder.bind(song)
    }

    override fun getItemCount(): Int = list.size
}

class SimilarSongViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.song_item, parent, false)) {
    private var title: TextView? = null
    private var author: TextView? = null
    private var albumImage: ImageView? = null
    private var time: TextView? = null


    init {
        title = itemView.findViewById(R.id.song_title)
        author = itemView.findViewById(R.id.song_author)
        albumImage = itemView.findViewById(R.id.AlbumImage)
        time = itemView.findViewById(R.id.time)
    }

    @SuppressLint("SetTextI18n")
    fun bind(track: SimilarTrack) {
        var imageUrl = ""
        title?.text = track.name
        author?.text = track.artist.name

        track.image.forEach(){
            if(it.size == "large"){
                imageUrl = it.text
            }
        }

        Picasso.get().load(imageUrl).into(albumImage);
        time?.text = ""

    }

}
