package com.example.flixster_two

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.serialization.json.Json
import com.example.flixster_two.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONException

/*
fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}
*/
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
private const val SEARCH_URL = "https://api.themoviedb.org/3/tv/popular?api_key=${API_KEY}&language=en-US&page=1"
private const val TAG = "Main Activity/"

class MainActivity : AppCompatActivity() {
    private val shows = mutableListOf<Show>()
    private lateinit var showsRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        showsRecyclerView = findViewById(R.id.list)

        showsRecyclerView.layoutManager = LinearLayoutManager(this).also{
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            showsRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        val showAdapter = ShowAdapter(this, shows)
        showsRecyclerView.adapter = showAdapter

        val client = AsyncHttpClient()
        client.get(SEARCH_URL, object: JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch data: $statusCode")
            }
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON){
                Log.i(TAG, "Successfully fetched data: $json")
                try {
                    /*val parsedJson = createJson().decodeFromString(Response.serializer(), json.jsonObject.toString())
                    parsedJson.response?.let{
                        list -> shows.addAll(list)
                        showAdapter.notifyDataSetChanged()
                    }*/
                    val showsRawJSON : String = json.jsonObject.get("results").toString()
                    val gson = Gson()
                    val arrayShowType = object : TypeToken<List<Show>>() {}.type

                    shows.addAll(gson.fromJson(showsRawJSON, arrayShowType))
                    showAdapter.notifyDataSetChanged()

                } catch (e: JSONException){
                    Log.e(TAG, "Exception: $e")
                }
            }
        })
    }
}