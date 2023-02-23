package com.example.flixster_two

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

const val SHOW_EXTRA = "SHOW_EXTRA"
class ShowAdapter(private val context: Context, private val shows: List<Show>) : RecyclerView.Adapter<ShowAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_shows, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = shows.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val show = shows[position]
        holder.bind(show)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        private val titleTextView = itemView.findViewById<TextView>(R.id.mediaTitle)
        private val mediaImageView = itemView.findViewById<ImageView>(R.id.mediaImage)

        init{ itemView.setOnClickListener(this) }
        fun bind(show: Show){
            titleTextView.text = show.name
            val posterURL = "https://image.tmdb.org/t/p/w500/" + show.posterPath
            Glide.with(context).load(posterURL).into(mediaImageView)
        }
        override fun onClick(v: View?) {
            val show = shows[absoluteAdapterPosition]
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(SHOW_EXTRA, show)
            context.startActivity(intent)
        }
    }
}