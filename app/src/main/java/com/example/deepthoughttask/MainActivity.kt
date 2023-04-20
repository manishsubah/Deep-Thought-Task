package com.example.deepthoughttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deepthoughttask.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)


        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dev.deepthought.education/assets/uploads/files/others/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<apiData> {
            override fun onResponse(call: Call<apiData>, response: Response<apiData>) {
                val responseBody = response.body()
                val itemData = responseBody?.response?.data!!
                myAdapter = MyAdapter(this@MainActivity, itemData)
                binding.recordsShow.adapter = myAdapter
                binding.recordsShow.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<apiData>, t: Throwable) {
                Log.d("Main Activity", "onFailure" + t.message)
            }

        })

    }


}