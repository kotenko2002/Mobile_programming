package com.example.lab9.adapters

import DbPostViewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.lab9.R
import com.example.lab9.models.ApiPost
import com.example.lab9.models.DbPost
import java.util.UUID
import com.example.lab9.db.PostRepository

class ApiPostAdapter(
    private val posts: List<ApiPost>,
    private val dbPostViewModel: DbPostViewModel,
    private val postRepository: PostRepository
) : RecyclerView.Adapter<ApiPostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.post_title)
        private val bodyTextView: TextView = itemView.findViewById(R.id.post_body)

        fun bind(post: ApiPost) {
            titleTextView.text = post.title
            bodyTextView.text = post.body

            itemView.setOnClickListener {
                val newPost = DbPost(
                    id = UUID.randomUUID().toString(),
                    title = post.title,
                    body = post.body
                )

                postRepository.addPost(newPost)

                dbPostViewModel.addNewPost(newPost)
                Toast.makeText(itemView.context, "Запис додано до улюблених постів", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
