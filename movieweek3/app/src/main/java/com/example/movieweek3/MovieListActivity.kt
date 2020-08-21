package com.example.movieweek3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_movie_list.*
import org.json.JSONException

class MovieListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        var requestQueue: RequestQueue = Volley.newRequestQueue(this)
        var gson: Gson = Gson()

        val url = "https://api.themoviedb.org/3/movie/now_playing?api_key=039eb2d8d4cb4d75a47e0256f4e51aff&language=ko-KR&region=KR"

        val request = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener { response -> try {
            val data = gson.fromJson<MovieList>(response.toString(), MovieList::class.java)
            val adapter = MovieAdapter(this, data.results)
            movieRecycler.adapter = adapter

            val lm = LinearLayoutManager(this)
            movieRecycler.layoutManager = lm
        } catch(e:JSONException){
            Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
        }, Response.ErrorListener {
            error -> Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
        })
        requestQueue.add(request)


    }
}