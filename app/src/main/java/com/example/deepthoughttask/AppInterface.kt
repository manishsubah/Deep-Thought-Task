package com.example.deepthoughttask

import retrofit2.Call
import retrofit2.http.GET

interface AppInterface {

@GET("project.json")
fun getData() : Call<apiData>


}