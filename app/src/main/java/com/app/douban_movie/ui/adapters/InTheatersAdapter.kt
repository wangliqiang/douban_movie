package com.app.douban_movie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.douban_movie.data.model.Subject
import com.app.douban_movie.databinding.ItemInTheatersRecyclerviewBinding
import com.google.common.base.Strings

class InTheatersAdapter : ListAdapter<Subject, InTheatersAdapter.ViewHolder>(diffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemInTheatersRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.apply {
            bind(item)
            itemView.tag = item
        }


        val pubCountry = StringBuffer()
        for (i in item.pubdates.indices) {
            item.pubdates[i].indexOf("(")
            pubCountry.append(
                item.pubdates[i].substring(
                    item.pubdates[i].indexOf("(") + 1,
                    item.pubdates[i].indexOf(")")
                ) + " "
            )
        }

        val genres = StringBuffer()
        for (i in item.genres.indices) {
            genres.append(item.genres[i] + " ")
        }

        val casts = StringBuffer()
        for (i in item.casts.indices) {
            casts.append(item.casts[i].name + " ")
        }

        holder.binding.itemDescription.text = (
                item.year + " / " + pubCountry.toString() + " / " + genres.toString() + (if (item.directors.isEmpty()) " " else " / " +
                        item.directors.get(0).name) + if (Strings.isNullOrEmpty(casts.toString())) " " else " / $casts"
                )

    }

    inner class ViewHolder(val binding: ItemInTheatersRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(subjects: Subject) {
            binding.apply {
                subject = subjects
                executePendingBindings()
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Subject>() {
            override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean =
                oldItem == newItem
        }
    }
}