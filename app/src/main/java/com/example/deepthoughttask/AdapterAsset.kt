package com.example.deepthoughttask

import android.app.Activity
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterAsset(private val context: Activity, private val assetData: List<Asset>) :
RecyclerView.Adapter<AdapterAsset.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_item_asset, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return assetData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val assetDataPosition = assetData[position]
        holder.assetTitle.text = assetDataPosition.asset_title
        holder.assetDescription.text = assetDataPosition.asset_description
        holder.assetContent.text = assetDataPosition.asset_content
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var assetTitle : TextView
        lateinit var assetDescription : TextView
        lateinit var assetContent : TextView
        lateinit var gotoBtn : Button

        init {
            assetTitle = itemView.findViewById(R.id.asTitle)
            assetDescription = itemView.findViewById(R.id.asDescription)
            assetContent = itemView.findViewById(R.id.assContent)
            gotoBtn = itemView.findViewById(R.id.goTo)
        }

    }


}