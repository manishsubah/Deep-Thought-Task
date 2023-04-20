package com.example.deepthoughttask

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val context: Activity, private val dataList : List<Data>) :
RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.shortDescription.text = currentItem.short_description
        holder.description.text = currentItem.description
        holder.learningOutcome.text = currentItem.learning_outcomes.toString()
        holder.preRequisites.text = currentItem.pre_requisites.toString()
        holder.fullName.text = currentItem.recruiter.fullname

        holder.exploreBtn.setOnClickListener {
            val taskId = currentItem.tasks[0].task_id
            val taskTitle = currentItem.tasks[0].task_title
            val taskDescription = currentItem.tasks[0].task_description

            val intent = Intent(context, taskAsset::class.java)
            intent.putExtra("pos", position)
            intent.putExtra("id", taskId)
            intent.putExtra("title", taskTitle)
            intent.putExtra("description", taskDescription)
            context.startActivity(intent)

        }

    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
            lateinit var description : TextView
            lateinit var learningOutcome : TextView
            lateinit var preRequisites : TextView
            lateinit var shortDescription : TextView
            lateinit var fullName : TextView
            lateinit var exploreBtn : Button

            init {
                description = itemView.findViewById(R.id.description)
                shortDescription = itemView.findViewById(R.id.shortDescription)
                learningOutcome = itemView.findViewById(R.id.learnOutcome)
                preRequisites = itemView.findViewById(R.id.pDescription)
                fullName = itemView.findViewById(R.id.name)
                exploreBtn = itemView.findViewById(R.id.button)

            }


    }

}