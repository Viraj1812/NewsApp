import android.util.Log
import com.google.gson.Gson
import com.moengage.newsapp.viewModel.Article
import com.moengage.newsapp.viewModel.NewsModel
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class RemoteApi {

    val BASE_URL = "https://candidate-test-data-moengage.s3.amazonaws.com/Android/news-api-feed/staticResponse.json"

    fun getNews(article : ArrayList<Article>, onSuccess: () -> Unit, onError: (String) -> Unit){
        Thread(Runnable {
            val connection = URL(BASE_URL).openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Accept", "application/json")
            connection.connectTimeout = 10000
            connection.readTimeout = 10000
            connection.doInput = true

            try {
                val reader = InputStreamReader(connection.inputStream)

                reader.use { input ->
                    val response = StringBuilder()
                    val bufferedReader = BufferedReader(input)

                    bufferedReader.forEachLine {
                        response.append(it.trim())
                    }

                    val jsonString = response.toString()
                    val newsModel = Gson().fromJson(jsonString, NewsModel::class.java)

                    article.clear()
                    article.addAll(newsModel.articles)
                    onSuccess()
                }

            } catch (e: Exception) {
                onError(e.message ?: "Unknown error occurred")
            }

            connection.disconnect()

        }).start()
    }
}
