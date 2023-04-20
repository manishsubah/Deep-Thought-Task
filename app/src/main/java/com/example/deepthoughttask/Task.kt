package com.example.deepthoughttask

data class Task(
    val assets: List<Asset>,
    val status: String,
    val task_description: String,
    val task_id: Int,
    val task_title: String
)