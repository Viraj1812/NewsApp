package com.moengage.newsapp.activity

import RemoteApi
import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moengage.newsapp.R
import com.moengage.newsapp.networking.NetworkChecker
import com.moengage.newsapp.viewModel.Article


class MainActivity : AppCompatActivity() {
    private lateinit var articles: ArrayList<Article>
    private lateinit var newsRv: RecyclerView
    private lateinit var loadingPB: ProgressBar
    private lateinit var adapter: NewsRVAdapter

    private val networkChecker by lazy {
        NetworkChecker(getSystemService(ConnectivityManager::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        newsRv = findViewById(R.id.idRVNews)
        loadingPB = findViewById(R.id.idPBLoading)
        articles = ArrayList()
        adapter = NewsRVAdapter(articles, this)
        newsRv.layoutManager = LinearLayoutManager(this)
        newsRv.adapter = adapter

        getNews()

        val imageMenu = findViewById<ImageView>(R.id.imageMenu)
        imageMenu.setOnClickListener { v ->
            showPopupMenu(v)
        }
    }

    //show Popup
    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_new_to_old -> {
                    sortArticlesByNewToOld()
                    true
                }
                R.id.action_old_to_new -> {
                    sortArticlesByOldToNew()
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

    //for sorting based on publish date
    private fun sortArticlesByNewToOld() {
        articles.sortByDescending { it.publishedAt }
        adapter.notifyDataSetChanged()
    }

    private fun sortArticlesByOldToNew() {
        articles.sortBy { it.publishedAt }
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_new_to_old -> {
                return true
            }
            R.id.action_old_to_new -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getNews() {
        loadingPB.visibility = View.VISIBLE
        RemoteApi().getNews(articles,
            onSuccess = {
                runOnUiThread {
                    loadingPB.visibility = View.GONE
                    adapter.notifyDataSetChanged()
                }
            },
            onError = { errorMessage ->
                runOnUiThread {
                    loadingPB.visibility = View.GONE
                    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                }
            })
    }
}
