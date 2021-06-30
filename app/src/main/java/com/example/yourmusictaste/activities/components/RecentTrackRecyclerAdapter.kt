package com.example.yourmusictaste.activities.components

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yourmusictaste.R
import com.example.yourmusictaste.activities.api.Track
import com.squareup.picasso.Picasso

class RecentTrackRecyclerAdapter constructor(private val list: List<Track>, val clickListener: (Track) -> Unit):
    RecyclerView.Adapter<SongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SongViewHolder(
            inflater,
            parent
        )
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song: Track = list[position]
        holder.itemView.setOnClickListener{clickListener(song)}
        holder.bind(song)
    }

    override fun getItemCount(): Int = list.size
}

class SongViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
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
    fun bind(track: Track) {
        var imageUrl = ""
        title?.text = track.name
        author?.text = track.artist.text

        track.image.forEach(){
            if(it.size == "large"){
                imageUrl = it.text
            }
        }

        Picasso.get().load(imageUrl).into(albumImage);

        if (track.attr != null && track.attr.nowplaying == "true"){
            time?.text = "Playing..."
        } else {
            time?.text = track.date.text
        }

    }

}