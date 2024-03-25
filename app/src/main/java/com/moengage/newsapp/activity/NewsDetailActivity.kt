package com.moengage.newsapp.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.moengage.newsapp.R
import com.squareup.picasso.Picasso


class NewsDetailActivity : AppCompatActivity() {

    private var title: String? = null
    private var author: String? = null
    private var content: String? = null
    private var imageURL: String? = null
    private var url: String? = null
    private lateinit var titleTV: TextView
    private lateinit var authorTV: TextView
    private lateinit var contentTV:TextView
    private lateinit var newsIV: ImageView
    private lateinit var readNewsBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news_detail)

        title = getIntent().getStringExtra("title");
        author = getIntent().getStringExtra("author");
        content = getIntent().getStringExtra("content");
        imageURL = getIntent().getStringExtra("image");
        url = getIntent().getStringExtra("url");

        titleTV = findViewById(R.id.idTVTitle);
        authorTV = findViewById(R.id.idTVAuthor);
        contentTV = findViewById(R.id.idTVContent);

        newsIV = findViewById(R.id.idIVNews);

        readNewsBtn = findViewById(R.id.idBtnReadNews);
        titleTV.setText(title);
        authorTV.setText("Author : $author");
        contentTV.setText(content);
        Picasso.get().load(imageURL).into(newsIV);
        readNewsBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(url))
            startActivity(intent)
        }

    }
}