package com.example.flixster_two

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity(){
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var overviewTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mediaImageView = findViewById(R.id.mediaImage)
        titleTextView = findViewById(R.id.mediaTitle)
        overviewTextView = findViewById(R.id.mediaOverview)

        val show = intent.getSerializableExtra(SHOW_EXTRA) as Show

        titleTextView.text = show.name
        overviewTextView.text = show.overview
        val backdropURL = "https://image.tmdb.org/t/p/w500/" + show.backdropPath

        Glide.with(this).load(backdropURL).into(mediaImageView)
    }
}