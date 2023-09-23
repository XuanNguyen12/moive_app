package com.kma.movies.ui.details.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kma.movies.databinding.ItemMovieReviewBinding
import com.kma.movies.model.GetCommentResponse
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CommentListAdapter(val context: Context?, var items: List<GetCommentResponse> = ArrayList()) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMovieReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = items[position]
        holder.tvName.text = comment.userId?.username
        holder.tvComment.text = comment.content
        holder.tvDate.text = comment.createdAt?.let { convertTime(it) }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("SimpleDateFormat")
    private fun convertTime(timestamp: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = dateFormat.parse(timestamp)
        return date?.let { SimpleDateFormat("yyyy-MM-dd").format(it) } ?: ""
    }

    fun fillList(items: List<GetCommentResponse>) {
        this.items = items
        notifyDataSetChanged()
    }


}

class ViewHolder(binding: ItemMovieReviewBinding) : RecyclerView.ViewHolder(binding.root) {
    val tvName: TextView = binding.name
    val tvDate: TextView = binding.commentDate
    val tvComment: TextView = binding.comment
}