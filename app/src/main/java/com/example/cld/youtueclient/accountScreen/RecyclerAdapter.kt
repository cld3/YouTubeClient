package com.example.cld.youtueclient.accountScreen

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.cld.youtueclient.R
import com.example.cld.youtueclient.dataLayer.Channel
import com.example.cld.youtueclient.dataLayer.SearchListItem
import com.example.cld.youtueclient.searchScreen.OnRecyclerItemClickListener
import com.squareup.picasso.Picasso

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var items: MutableList<Channel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.channel, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = items[position].title
        Picasso.get().load(items[position].imageUrl).into(holder.image)
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val title = view.findViewById<TextView>(R.id.chanelTitle)
        val image = view.findViewById<ImageView>(R.id.chanelImage)

        init {
        }

    }

}