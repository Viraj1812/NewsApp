package com.moengage.newsapp.activity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moengage.newsapp.R
import com.moengage.newsapp.viewModel.Article
import com.squareup.picasso.Picasso

class NewsRVAdapter(articlesArrayList: ArrayList<Article>, context: Context) :
    RecyclerView.Adapter<NewsRVAdapter.ViewHolder>() {
    private val articlesArrayList: ArrayList<Article>
    private val context: Context

    init {
        this.articlesArrayList = articlesArrayList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.news_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val articles: Article = articlesArrayList[position]
        holder.subTitleTV.setText(articles.description)
        holder.titleTV.setText(articles.title)
        Picasso.get().load(articles.urlToImage).into(holder.newsIV)
        holder.itemView.setOnClickListener { view: View? ->
            val intent = Intent(
                context,
                NewsDetailActivity::class.java
            )
            intent.putExtra("title", articles.title)
            intent.putExtra("content", articles.content)
            intent.putExtra("author", articles.author)
            intent.putExtra("image", articles.urlToImage)
            intent.putExtra("url", articles.url)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return articlesArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTV: TextView
        val subTitleTV: TextView
        val newsIV: ImageView

        init {
            titleTV = itemView.findViewById<TextView>(R.id.idTVNewsHeading)
            subTitleTV = itemView.findViewById<TextView>(R.id.idTVSubTitle)
            newsIV = itemView.findViewById<ImageView>(R.id.idIVNews)
        }
    }
}