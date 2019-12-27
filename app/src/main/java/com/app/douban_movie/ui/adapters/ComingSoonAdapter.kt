package com.app.douban_movie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.douban_movie.data.model.Subject
import com.app.douban_movie.databinding.ItemCommingSoonRecyclerviewBinding
import com.google.common.base.Strings


class ComingSoonAdapter :
    ListAdapter<Subject, ComingSoonAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemCommingSoonRecyclerviewBinding.inflate(
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
        for (i in 0 until item.pubdates.size) {
            item.pubdates.get(i).indexOf("(")
            pubCountry.append(
                item.pubdates.get(i).substring(
                    item.pubdates.get(i).indexOf("(") + 1,
                    item.pubdates.get(i).indexOf(")")
                ) + " "
            )
        }

        val genres = StringBuffer()
        for (i in 0 until item.genres.size) {
            genres.append(item.genres.get(i) + " ")
        }

        val casts = StringBuffer()
        for (i in 0 until item.casts.size) {
            casts.append(item.casts.get(i).name + " ")
        }

        holder.binding.itemDescription.setText(
            item.year + " / " + pubCountry.toString() + " / " + genres.toString() + (if (item.directors.isEmpty()) " " else " / " + item.directors.get(
                0
            ).name)
                    + if (Strings.isNullOrEmpty(casts.toString())) " " else " / $casts"
        )
    }

    class ViewHolder(val binding: ItemCommingSoonRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(subjectsBean: Subject?) {
            binding.apply {
                subject = subjectsBean
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