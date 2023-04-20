package com.example.deepthoughttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.properties.Delegates

class taskAsset : AppCompatActivity() {
    lateinit var taskId : String
    var positionInd by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_asset)

        val taskTitle = findViewById<TextView>(R.id.tTitle)
        val taskDescription = findViewById<TextView>(R.id.tDescription)
        val recyclerData = findViewById<RecyclerView>(R.id.assetData)

        val bundle : Bundle? = intent.extras
        taskId = bundle!!.getInt("id").toString()
        taskTitle.text = bundle.getString("title")
        taskDescription.text = bundle.getString("description")
        positionInd = bundle.getInt("pos")

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dev.deepthought.education/assets/uploads/files/others/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppInterface::class.java)
        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<apiData> {
            override fun onResponse(call: Call<apiData>, response: Response<apiData>) {
                val responseBody = response.body()
                val itemData = responseBody?.response?.data?.get(positionInd)?.tasks?.get(0)?.assets!!
                val adapter = AdapterAsset(this@taskAsset, itemData)
                recyclerData.adapter = adapter
                recyclerData.layoutManager = LinearLayoutManager(this@taskAsset)
            }

            override fun onFailure(call: Call<apiData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }
}