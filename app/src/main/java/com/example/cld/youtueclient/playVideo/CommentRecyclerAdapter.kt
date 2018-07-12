package com.example.cld.youtueclient.playVideo

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.cld.youtueclient.R
import com.example.cld.youtueclient.dataLayer.Comment
import com.squareup.picasso.Picasso


class CommentRecyclerAdapter : RecyclerView.Adapter<CommentRecyclerAdapter.ViewHolder>() {

    var items: MutableList<Comment> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.authorName.text = items[position].authorName
        holder.text.text = items[position].text
        holder.date.text = items[position].publishedAt
        Log.d("qq","imageUrr ${items[position].authorImageUrl}")
        Picasso.get().load(items[position].authorImageUrl).into(holder.image)
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val authorName = view.findViewById<TextView>(R.id.authorName)
        val text = view.findViewById<TextView>(R.id.textComment)
        val image = view.findViewById<ImageView>(R.id.authorImage)
        val date = view.findViewById<TextView>(R.id.commentDate)

        init {
        }

    }

}