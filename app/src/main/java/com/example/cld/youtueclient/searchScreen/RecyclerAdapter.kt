package com.example.cld.youtueclient.searchScreen

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.cld.youtueclient.R
import com.example.cld.youtueclient.dataLayer.SearchListItem
import com.squareup.picasso.Picasso


class RecyclerAdapter(var onRecyclerItemClickListener: OnRecyclerItemClickListener) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var items: MutableList<SearchListItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = items[position].title
        holder.publishedAt.text = items[position].publishedAt
        Picasso.get().load(items[position].imageUrl).into(holder.image)
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val title = view.findViewById<TextView>(R.id.title)
        val publishedAt = view.findViewById<TextView>(R.id.publishedAt)
        val image = view.findViewById<ImageView>(R.id.imageView)

        init {
            view.findViewById<CardView>(R.id.cardItem)
                    .setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            onRecyclerItemClickListener.onItemClick(items[adapterPosition])
        }
    }

}