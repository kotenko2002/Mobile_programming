package com.example.lab9.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab9.R
import com.example.lab9.models.DbPost

class DbPostAdapter(private var posts: List<DbPost>, private val onItemClick: (DbPost) -> Unit)
    : RecyclerView.Adapter<DbPostAdapter.PostViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorite_item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.postTitleTextView)

        fun bind(post: DbPost) {
            titleTextView.text = post.title

            itemView.setOnClickListener { onItemClick(post) }
        }
    }

    fun updatePosts(newPosts: List<DbPost>) {
        posts = newPosts
        notifyDataSetChanged()
    }
}
